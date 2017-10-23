package invoice.iceason.isposbo.service;

import invoice.iceason.core.DatabaseContextHolder;
import invoice.iceason.fapiao.entityjs.BillingResult;
import invoice.iceason.isposbo.dao.CancelOrderDao;
import invoice.iceason.isposbo.dao.OrderDiscountDao;
import invoice.iceason.isposbo.dao.OrderElementItemDao;
import invoice.iceason.isposbo.dao.OrderItemDao;
import invoice.iceason.isposbo.dao.OrderPaymentDao;
import invoice.iceason.isposbo.dao.PosOrderDao;
import invoice.iceason.isposbo.dao.RefundOrderDao;
import invoice.iceason.isposbo.entity.CancelOrder;
import invoice.iceason.isposbo.entity.OrderPayment;
import invoice.iceason.isposbo.entity.PosOrder;
import invoice.iceason.isposbo.entity.RefundOrder;
import invoice.iceason.utility.ConstantStaticVariablesSet;
import invoice.iceason.utility.ResponseData;
import invoice.iceason.weixin.entity.MemberPayment;
import invoice.iceason.weixin.entity.PaymentWay;
import invoice.iceason.weixin.entity.StoreList;
import invoice.iceason.weixin.service.BaseService;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PosOrderService extends BaseService<PosOrder>{

	private PosOrderDao posOrderDao;
	@Resource
	private CancelOrderDao cancelOrderDao;
	@Resource
	private OrderDiscountDao orderDiscountDao;
	@Resource
	private OrderElementItemDao orderElementItemDao;
	@Resource
	private OrderItemDao orderItemDao;
	@Resource
	private OrderPaymentDao orderPaymentDao;
	@Resource
	private RefundOrderDao refundOrderDao;

	@Autowired
	public PosOrderService(PosOrderDao posOrderDao){
		super(posOrderDao);
		this.posOrderDao = posOrderDao;
	}
	/**
	 * 是否允许开发票
	 */
	public ResponseData isInvoice(String orderkey,List<MemberPayment> listMemberPayment,List<PaymentWay> listPaymentWay,List<StoreList> listStoreList){
		ResponseData responseData = new ResponseData();
		List<CancelOrder> listCancelOrder = cancelOrderDao.findBy("orderKey", orderkey);
		List<RefundOrder> listRefundOrder = refundOrderDao.findBy("orderKey", orderkey +1);
		if((listCancelOrder == null || listCancelOrder.size() == 0)&&
				(listRefundOrder == null || listRefundOrder.size() == 0) ){
			PosOrder posOrder = posOrderDao.findUniqueBy("orderKey",orderkey);
			if(posOrder != null){
				boolean boo =true;
				for (StoreList storeList : listStoreList) {
					if(storeList.getStoreid().equals(posOrder.getStoreId())){
						boo =false;
						break;
					}
				}
				if(boo){
					boolean boo1 = false;
					if(posOrder.getOrderStatus().equals("100") || posOrder.getOrderStatus() == 100){
						List<OrderPayment> orderPayment =  orderPaymentDao.findBy("orderKey",orderkey);
						for(int j = 0;j<orderPayment.size();j++){
							for(int i = 0 ; i < listPaymentWay.size(); i++){
								if(orderPayment.get(j).getTenderId().equals(listPaymentWay.get(i).getTenderId())){
									responseData.setSuccess(true);
									responseData.setMessage("验证成功");
									//orderkey、总金额、支付方式
									boo1 =true;
									break;
								}
							}
							if(boo1){
								break;
							}
						}
					}else if(posOrder.getOrderStatus().equals("112") || posOrder.getOrderStatus() == 112){
						List<OrderPayment> orderPayment =  orderPaymentDao.findBy("orderKey",orderkey);
						 boo1 = false;
						for(int j = 0;j<orderPayment.size();j++){
							for(int i = 0 ; i < listMemberPayment.size(); i++){
								if(orderPayment.get(j).getTenderId().equals(listMemberPayment.get(i).getTenderId())){
									responseData.setSuccess(true);responseData.setMessage("验证成功");
									boo1 =true;
									break;
								}
							}
							if(boo1){
								break;
							}
						}
					}else{
						responseData.setSuccess(false);
						responseData.setMessage("当前支付方式无法开发票");
					}
				}
			}
		}else{
			responseData.setSuccess(false);
			responseData.setMessage("当前订单退款后无法开发票");
		}
		return responseData;
	}
	/**	 
	 * 
	* @Title: getInvoice
	* @Description: TODO( 根据 orderKEY 获取 订单  开发票的  基本信息用开发票   还需要税率
	* @param @param orderkey
	* @param @return    设定文件
	* @return boolean    返回类型
	* @throws
	 */
	public BillingResult getInvoice(String orderkey,List<MemberPayment> listMemberPayment,List<PaymentWay> listPaymentWay,List<StoreList> listStoreList){
		//ResponseData responseData = new ResponseData();
		BillingResult billingResult = new BillingResult();
		DatabaseContextHolder.setCustomerType(DatabaseContextHolder.SESSION_FACTORY_BO);
		List<CancelOrder> listCancelOrder = cancelOrderDao.findBy("orderKey", orderkey);
		List<RefundOrder> listRefundOrder = refundOrderDao.findBy("orderKey", orderkey +1);
		if((listCancelOrder == null || listCancelOrder.size() == 0)&& (listRefundOrder == null || listRefundOrder.size() == 0) ){
			PosOrder posOrder =  posOrderDao.findUniqueBy("orderKey",orderkey);
			//DatabaseContextHolder.setCustomerType(DatabaseContextHolder.SESSION_FACTORY_INVOICE);
			if(posOrder != null){
				boolean boo =true;
				for (StoreList storeList : listStoreList) {
					if(storeList.getStoreid().equals(posOrder.getStoreId())){
						boo =false;
						break;
					}
				}
				if(boo){
					billingResult.setOrderid(posOrder.getOrderId());
					billingResult.setOrdertime(posOrder.getCreateTime());
					if(posOrder.getOrderStatus()==Integer.valueOf(ConstantStaticVariablesSet.payWay1)){
						List<OrderPayment> orderPayment = orderPaymentDao.findBy("orderKey",orderkey);
						Double total = 0.00;
						for(int j = 0 ; j < orderPayment.size(); j ++){
							for(int i = 0 ; i < listPaymentWay.size(); i++){
								if(orderPayment.get(j).getTenderId().equals(listPaymentWay.get(i).getTenderId())){
									total+=orderPayment.get(j).getPayValue()+orderPayment.get(j).getOverCharged();
								}
							}
						}
						billingResult.setOrderKey(orderkey);
						billingResult.setPayWay(ConstantStaticVariablesSet.payWay1);
						billingResult.setTotalFee(total);
					}else if(posOrder.getOrderStatus()==Integer.valueOf(ConstantStaticVariablesSet.payWay2) ){
						List<OrderPayment> orderPayment =  orderPaymentDao.findBy("orderKey",orderkey);
						Double total = 0.00;
						for(int j = 0 ;j < orderPayment.size(); j++){
							for(int i = 0 ; i < listMemberPayment.size(); i++){
								if(orderPayment.get(j).getTenderId().equals(listMemberPayment.get(i).getTenderId())){
									total+=orderPayment.get(j).getPayValue()+orderPayment.get(j).getOverCharged();
									//responseData.setSuccess(true);
								}
							}
						}
						billingResult.setOrderKey(orderkey);
						billingResult.setPayWay(ConstantStaticVariablesSet.payWay2);
						billingResult.setTotalFee(total);
						
					}else{
						//responseData.setSuccess(false);
					}
				}
			}
		}else{
			//responseData.setSuccess(false);
		}
		//return responseData;
		return billingResult;
	}
}
