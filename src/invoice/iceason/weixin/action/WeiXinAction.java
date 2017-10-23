package invoice.iceason.weixin.action;

import invoice.iceason.core.DatabaseContextHolder;
import invoice.iceason.debug.ApplicationListener;
import invoice.iceason.debug.DebugLog;
import invoice.iceason.fapiao.entityjs.BillingParamters;
import invoice.iceason.fapiao.entityjs.BillingParamtersInfo;
import invoice.iceason.fapiao.entityjs.BillingResult;
import invoice.iceason.fapiao.entityjs.ContentInfo;
import invoice.iceason.fapiao.entityjs.Interface;
import invoice.iceason.fapiao.entityjs.ProductsInfo;
import invoice.iceason.fapiao.entityjs.RequestOrder;
import invoice.iceason.fapiao.entityjs.UrlInfo;
import invoice.iceason.fapiao.utility.PDFDownloader;
import invoice.iceason.fapiao.utility.Utils;
import invoice.iceason.fapiao.utility.XmlUtils;
import invoice.iceason.isposbo.dao.OrderItemDao;
import invoice.iceason.isposbo.dao.OrderPaymentDao;
import invoice.iceason.isposbo.entity.OrderItem;
import invoice.iceason.isposbo.entity.OrderPayment;
import invoice.iceason.isposbo.entity.PosOrder;
import invoice.iceason.isposbo.service.OrderItemService;
import invoice.iceason.isposbo.service.OrderPaymentService;
import invoice.iceason.isposbo.service.PosOrderService;
import invoice.iceason.utility.ConstantStaticVariablesSet;
import invoice.iceason.utility.GsonJson;
import invoice.iceason.utility.ResponseData;
import invoice.iceason.utility.Utility;
import invoice.iceason.weixin.entity.InEnterprise;
import invoice.iceason.weixin.entity.InPersonal;
import invoice.iceason.weixin.entity.MemberPayment;
import invoice.iceason.weixin.entity.PaymentWay;
import invoice.iceason.weixin.entity.StoreList;
import invoice.iceason.weixin.entityjs.Enterprise;
import invoice.iceason.weixin.entityjs.Personal;
import invoice.iceason.weixin.service.InEnterpriseService;
import invoice.iceason.weixin.service.InPersonalService;
import invoice.iceason.weixin.service.MemberPaymentService;
import invoice.iceason.weixin.service.PaymentWayService;
import invoice.iceason.weixin.service.StoreListService;
import invoice.iceason.weixin.utility.AccessTokenURL;
import invoice.iceason.weixin.utility.MessageUtil;
import invoice.iceason.weixin.utility.UserInfo;
import invoice.iceason.weixin.utility.WebAuthorizationUser;
import invoice.iceason.weixin.utility.WeixinUtil;
import invoice.iceason.weixin.weixin.JSONutilss;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdk.nashorn.internal.runtime.logging.DebugLogger;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.JsonSyntaxException;
import com.sankuai.meituan.waimai.opensdk.vo.OrderParam;
import com.sun.org.apache.bcel.internal.generic.ARRAYLENGTH;

@Controller
@RequestMapping("weixin")
public class WeiXinAction {
	
	
	@RequestMapping(value="nopurl", method = RequestMethod.GET)
	public ModelAndView getnopurl(){
		ModelAndView view = new ModelAndView();
		//String url1 = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxa2ec4046c12f1c84&redirect_uri=http://wechat.talentsolution.net/Iceason_Invoice/weixin/index2.do&response_type=pos&scope=snsapi_base&state=2#wechat_redirect";
		String url1 = "index2";
		view.addObject("url", url1);
		String url = "jsp/lanid";
		view.setViewName(url);
		view.addObject("openid", "fdfaosd");
		//String url="index2";
		view.setViewName(url1);
		return view;
	}
	
	@RequestMapping(value="qrCode")
	public ModelAndView goQrCode(String xml){
		//取scanResult
		ModelAndView view = new ModelAndView();
		//view.setViewName("jsp/qrCode");
		return view;
	}
	@RequestMapping(value="goMyBilling")
	public ModelAndView goMyBilling(){
		ModelAndView view = new ModelAndView();
		
		String url1 = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxa2ec4046c12f1c84&redirect_uri=http://wechat.talentsolution.net/Iceason_Invoice/weixin/getBilling.do&response_type=pos&scope=snsapi_base&state=1#wechat_redirect";
		view.addObject("url", url1);
		String url = "jsp/lanid";
		view.setViewName(url);
		return view;
	}
	@RequestMapping(value="getBilling")
	public ModelAndView getBilling( ){
		ModelAndView view = new ModelAndView();
//		String name = WeixinUtil.createMenu_WebAccessTokenURL(accessTokenURL.getCode());
//		WebAuthorizationUser webAuthorizationUser = (WebAuthorizationUser) GsonJson.getGson().fromJson(name, WebAuthorizationUser.class);
//		String openid = webAuthorizationUser.getOpenid();
//		List<InEnterprise> inEnterprise = inEnterpriseService.findBy("openid", openid);
//		List<InPersonal> inPersonals = inPersonalService.findBy("openid", openid);
//		view.addObject("inpersonal", inPersonals);
//		view.addObject("inEnterprise", inEnterprise);
		view.setViewName("jsp/choose");
		view.addObject("yes", "yse");
//		if(inPersonals !=null && inPersonals.size()>0){
//			view.addObject("inpersonal", inPersonals);
//		}
//		if(inEnterprise!=null && inEnterprise.size()>0){
//			view.addObject("inEnterprise", inEnterprise);
//		}
//		if(inPersonals.size()==0 && inPersonals.size()== 0){
//			view.addObject("inEnterprise", "");
//		}
		
		return view;
		}
	@RequestMapping(value = "index2")
	public ModelAndView getIndex2(AccessTokenURL accessTokenURL,HttpServletRequest request) {
		ModelAndView view = new ModelAndView();
		String name = WeixinUtil.createMenu_WebAccessTokenURL(accessTokenURL.getCode());
		WebAuthorizationUser webAuthorizationUser = (WebAuthorizationUser) GsonJson.getGson().fromJson(name, WebAuthorizationUser.class);
		//view.addObject("openid", webAuthorizationUser.getOpenid());
		view.addObject("openid", request.getSession().getAttribute("openID"));
		String url = "index2";
		view.setViewName(url);
		return view;
	}
	
	@RequestMapping(value = "index")
	public ModelAndView getIndex(AccessTokenURL accessTokenURL,HttpServletRequest request,@RequestParam("state") String state) {
		ModelAndView view = new ModelAndView();
		view.setViewName("error");// 错误界面
		if (accessTokenURL.getState() != null) {
			// String scope=accessTokenURL.getScope();
			String name = WeixinUtil.createMenu_WebAccessTokenURL(accessTokenURL.getCode());
			WebAuthorizationUser webAuthorizationUser = (WebAuthorizationUser) GsonJson.getGson().fromJson(name, WebAuthorizationUser.class);
			if (webAuthorizationUser.getAccess_token() != null) {
				String nameWeb = WeixinUtil.createMenu_Web(webAuthorizationUser.getAccess_token(), webAuthorizationUser.getOpenid(), MessageUtil.WebAuthorization_lang_zh_CN);
				UserInfo userInfo = (UserInfo) GsonJson.getGson().fromJson(nameWeb, UserInfo.class);
				// 根据orderkey 获取 订单信息
				//String orderkey = accessTokenURL.getState();
				String orderkey = state;
				
				List<InPersonal> findBy = inPersonalService.findBy("orderkey", orderkey);
				if(findBy == null || findBy.size() == 0){//  没有开过  个人发票
					List<InEnterprise> findBy2 = inEnterpriseService.findBy("orderkey", orderkey);
					if(findBy2 == null || findBy2.size() == 0){ // 没有开过  企业发票
						//会员  包含支付方式
						List<MemberPayment> listMemberPayment = memberPaymentService.getAll();
						//正常产品  包含支付方式
						List<PaymentWay> listPaymentWay = paymentWayService.getAll();
						// 无法  开发票的   门店 列表
						List<StoreList> listStoreList = storeListService.getAll();
						
						DatabaseContextHolder.setCustomerType(DatabaseContextHolder.SESSION_FACTORY_BO);
						// 返回一个开票结果，价格，支付方式
						BillingResult invoice = posOrderService.getInvoice(orderkey,listMemberPayment,listPaymentWay,listStoreList);
						// 当前项目数据库
						DatabaseContextHolder.setCustomerType(DatabaseContextHolder.SESSION_FACTORY_INVOICE);
						
						if(invoice!=null){
							
						
						// 微信用  ID 
						//view.addObject("openid", webAuthorizationUser.getOpenid());
						view.addObject("openid", request.getSession().getAttribute("openID"));
						
						String openid = webAuthorizationUser.getOpenid();//userInfo.getOpenid();
						List<InPersonal> inPersonal = inPersonalService.findBy("openid", openid);
						Set<String> nameList = new HashSet<String>();
						for(InPersonal in:inPersonal){
							String namee = in.getPName();
							nameList.add(namee);
						}
						Set<String> phoneList = new HashSet<String>();
						for(InPersonal in:inPersonal){
							String phone = in.getPMphone();
							phoneList.add(phone);
						}
						Set<String> mailList = new HashSet<String>();
						for(InPersonal in:inPersonal){
							String mail = in.getPEmail();
							mailList.add(mail);
						}
						
						view.addObject("name", nameList);
						view.addObject("phone", phoneList);
						view.addObject("mail",mailList);

						List<InEnterprise> inEnterprises = inEnterpriseService.findBy("openid", openid);
						Set<String> enameList = new HashSet<String>();
						for(InEnterprise in:inEnterprises){
							String namew = in.getPName();
							enameList.add(namew);
						}
						Set<String> ephoneList = new HashSet<String>();
						for(InEnterprise in:inEnterprises){
							String phone = in.getEPhone();
							ephoneList.add(phone);
						}
						Set<String> emailList = new HashSet<String>();
						for(InEnterprise in:inEnterprises){
							String mail = in.getPEmail();
							emailList.add(mail);
						}
						Set<String> nsrList = new HashSet<String>();
						for(InEnterprise in:inEnterprises){
							String nsr = in.getNsrsbh();
							nsrList.add(nsr);
						}
						Set<String> bankList = new HashSet<String>();
						for(InEnterprise in:inEnterprises){
							String bank = in.getEBank();
							bankList.add(bank);
						}
						Set<String> accountList = new HashSet<String>();
						for(InEnterprise in:inEnterprises){
							String account = in.getEAccount();
							accountList.add(account);
						}
						Set<String> addressList = new HashSet<String>();
						for(InEnterprise in:inEnterprises){
							String address = in.getEAddress();
							addressList.add(address);
						}
						Set<String> dhList = new HashSet<String>();
						for(InEnterprise in:inEnterprises){
							String dh = in.getEPhone();
							dhList.add(dh);
						}
						view.addObject("ename", enameList);
						view.addObject("ephone", ephoneList);
						view.addObject("email", emailList);
						view.addObject("nsr", nsrList);
						view.addObject("bank", bankList);
						view.addObject("account", accountList);
						view.addObject("address", addressList);
						view.addObject("dh", dhList);
						
						// 正确界面
						view.setViewName("index");
						// 订单金额
						view.addObject("amount", invoice.getTotalFee());
						// 订单ID
						view.addObject("orderid", invoice.getOrderid());
						// 订单 类型   100  是 正常点餐订单    112  为 会员卡充值  订单
						view.addObject("payway", invoice.getPayWay());
						// 时间
						view.addObject("date", Utility.formatDateYMDHMS(invoice.getOrdertime()));
						/*String openid = "1";
						List<InPersonal> inPersonal = inPersonalService.findBy("openid", openid);
						view.addObject("personal", inPersonal);*/
					}
						view.setViewName("error");
					}
				}
			}
		}
		return view;
	}
	@Resource
	private InEnterpriseService inEnterpriseService;
	@Resource
	private InPersonalService inPersonalService;
	@Resource
	private PosOrderService posOrderService;
	@Resource
	private MemberPaymentService memberPaymentService;
	@Resource
	private PaymentWayService paymentWayService;
	@Resource
	private StoreListService storeListService;
	@RequestMapping(value = "purl", method = RequestMethod.GET)
	public ModelAndView getpurl(HttpServletRequest request, HttpServletResponse response, @RequestParam("reserved") String orderkey,@RequestParam("openID") String openID) {
		System.err.println("orderid:" + orderkey);
		ModelAndView view = new ModelAndView();
		view.addObject("orderkey", orderkey);
		
		request.getSession().setAttribute("openID", openID);
		if(orderkey!="1"&&!orderkey.equals("1")){
			
		
		ResponseData responseData = new ResponseData();
		responseData.setSuccess(false);
		List<InPersonal> findBy = inPersonalService.findBy("orderkey", orderkey);
		if(findBy == null || findBy.size() == 0){//  没有开过  个人发票  
			List<InEnterprise> findBy2 = inEnterpriseService.findBy("orderkey", orderkey);
			if(findBy2 == null || findBy2.size() == 0){ // 没有开过  企业发票
				List<MemberPayment> listMemberPayment = memberPaymentService.getAll();
				List<PaymentWay> listPaymentWay = paymentWayService.getAll();
				List<StoreList> listStoreList = storeListService.getAll();
				DatabaseContextHolder.setCustomerType(DatabaseContextHolder.SESSION_FACTORY_BO);
				responseData = posOrderService.isInvoice(orderkey,listMemberPayment,listPaymentWay,listStoreList);
				// 自己的 数据库
				DatabaseContextHolder.setCustomerType(DatabaseContextHolder.SESSION_FACTORY_INVOICE);
			}else{
				responseData.setSuccess(true);
				String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxa2ec4046c12f1c84&redirect_uri=http://wechat.talentsolution.net/Iceason_Invoice/weixin/subway.do&response_type=pos&scope=snsapi_base&state=1#wechat_redirect";
				//String url = "http://wechat.talentsolution.net/Iceason_Invoice/weixin/subway.do?state=1";
				//responseData.setMessage("pfapiao");
				view.setViewName(url);
				//DebugLog.logger.info("orderkey:"+orderkey+"被查看了发票信息！");
			}
		}else{
			responseData.setSuccess(true);
			String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxa2ec4046c12f1c84&redirect_uri=http://wechat.talentsolution.net/Iceason_Invoice/weixin/subway.do&response_type=pos&scope=snsapi_base&state=1#wechat_redirect";
			//String url = "http://http://wechat.talentsolution.net/Iceason_Invoice/weixin/subway.do?state=1";
			//responseData.setMessage("pfapiao");
			view.setViewName(url);
			//DebugLog.logger.info("orderkey"+orderkey+"被查看了发票信息！");
		}
		
		//true     view.getViewName()为空即表示用户是第一次使用这个orderKey来开票
		if(responseData.isSuccess()&&(view.getViewName().equals("")||view.getViewName()==null)){
			// BO数据库
			// orderid 根据orderKEY 判断当前订单是否可以开发票 如果可就跳转 URL
			String url1 = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxa2ec4046c12f1c84&redirect_uri=http://wechat.talentsolution.net/Iceason_Invoice/weixin/index.do&response_type=pos&scope=snsapi_base&state=" + orderkey + "#wechat_redirect";
			//String url = "http://wechat.talentsolution.net/Iceason_Invoice/weixin/index.do?state="+orderkey;
			view.addObject("url", url1);
			String url = "jsp/lanid";
			view.setViewName(url);
			DebugLog.logger.info("orderkey:"+orderkey+"开票开始！");
		}else if(!responseData.isSuccess()){
			String url = "error";
			view.addObject(responseData);
			view.setViewName(url);
		}
		}else{
		//	String url1 = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxa2ec4046c12f1c84&redirect_uri=http://wechat.talentsolution.net/Iceason_Invoice/weixin/index2.do&response_type=pos&scope=snsapi_base&state=" + orderkey + "#wechat_redirect";
			String url1 = "index2";
			//view.addObject("url", url1);
		//	String url = "jsp/lanid";
			view.addObject("openid", "fdfaosd");
			view.setViewName(url1);
		}
		return view;
	}

	
	
	
	
	/**
	 * 
	 * @Title: personal
	 * @Description: TODO( 个人 发票
	 * @param @param request
	 * @param @param response
	 * @param @param orderid
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	@Resource
	OrderItemService orderItemService;
	@Resource
	OrderPaymentService orderPaymentService;
	@ResponseBody
	@RequestMapping(value = "personal", produces = { "application/json;charset=UTF-8" }, method = RequestMethod.POST)
	public String personal(HttpServletRequest request, HttpServletResponse response, Personal personal) {
		ResponseData responseData = new ResponseData();
		System.err.println("orderid:" + "");
		personal.setAmount("5000");
		personal.setOpenid("2131");
		personal.setPayway(ConstantStaticVariablesSet.payWay2);
		//DebugLog.logger.info("
		//DebugLog.logger.error("");
		String openid = personal.getOpenid();
		if(openid !=null&&!openid.equals("")){
			// 调用接口开发票
			try {
				
				BillingParamtersInfo bi = BillingParamtersInfo.getbillBillingParamtersInfo();
				BillingParamters billingParamters =  new BillingParamters();
				personal2BillingParamters(personal, billingParamters);
				billingParamters.setPayway(personal.getPayway());
				//billingParamters.setBZ("测试一下");
				billingParamters.setFHR(bi.getFHR());
				String FPQQLSH = "TEST"+Utils.formatToTime()+"01";
				billingParamters.setFPQQLSH(FPQQLSH);
				
				billingParamters.setKPLX("0");
				billingParamters.setKPR(bi.getKPR());
				billingParamters.setSKR(bi.getSKR());
				billingParamters.setZSFS("0");
//				billingParamters.setXSF_YHZH(bi.getXSF_YHZH());
				billingParamters.setXSF_NSRSBH(bi.getXSF_NSRSBH());
				billingParamters.setXSF_MC(bi.getXSF_MC()); // 测试名称不能修改
				billingParamters.setXSF_DZDH(bi.getXSF_DHDZ());
				ProductsInfo proInfo = new ProductsInfo();
				List<ProductsInfo> prInfo = new ArrayList<ProductsInfo>();
				proInfo.setFPHXZ("0");
				double total = Double.valueOf(personal.getAmount());
				BigDecimal bd1 = new BigDecimal(0);
				if(personal.getPayway().equals(ConstantStaticVariablesSet.payWay1)){
					//冰淇淋&餐饮
					proInfo.setSPBM("1010101050000000000");
					proInfo.setXMMC("餐饮");
					proInfo.setSL("0.06");
					proInfo.setXMJE(personal.getAmount());//String.valueOf(total)
					BigDecimal bigDecimal = new BigDecimal(total);
					bd1 = bigDecimal.multiply(new BigDecimal(0.06)).setScale(2,BigDecimal.ROUND_HALF_UP);
					proInfo.setSE(String.valueOf(bd1));
					prInfo.add(proInfo);
				}else if(personal.getPayway().equals(ConstantStaticVariablesSet.payWay2) ){
					//会员卡消费
					proInfo.setSPBM("6010000000000000000");
					proInfo.setXMMC("会员卡消费");
					proInfo.setSL("0");
					proInfo.setXMJE(personal.getAmount());
					BigDecimal bigDecimal = new BigDecimal(total);
					bd1 =   bigDecimal.multiply(new BigDecimal(0.00)).setScale(2,BigDecimal.ROUND_HALF_UP);
					proInfo.setSE(String.valueOf(bd1));
					prInfo.add(proInfo);
				}	
				billingParamters.setHJSE(bd1.toString());
				billingParamters.setHJJE(personal.getAmount());
				billingParamters.setJSHJ(bd1.add(new BigDecimal(personal.getAmount())).setScale(2,BigDecimal.ROUND_HALF_UP).toString());
				billingParamters.setpInfo(prInfo);
				String faPiao = Utils.getFaPiao(billingParamters);
				String fap = faPiao.substring(13, faPiao.length()-1);
				Interface fromJson = GsonJson.getGson().fromJson(fap, Interface.class);
				String content = fromJson.getData().getContent();
				String returnCode = fromJson.getReturnStateInfo().getReturnCode();
				String returnMessage = fromJson.getReturnStateInfo().getReturnMessage();
				System.out.println(content);
				if(returnCode.equals("0000")){
					//正确返回
					InPersonal inPersonal = new InPersonal();
					inPersonal.setCreatetime(new Date());
					inPersonal.setAmount(personal.getAmount());
					inPersonal.setOrderid(personal.getOrderid());
					inPersonal.setOpenid(personal.getOpenid());
					inPersonal.setOrderkey(personal.getOrderkey());
					inPersonal.setPEmail(personal.getP_email());
					inPersonal.setPMphone(personal.getP_mphone());
					inPersonal.setUpdatetime(new Date());
					inPersonal.setPName(personal.getP_name());
					
					//System.out.println(inPersonalService.save(inPersonal));
					
					// 把content 64解密
					String contentInfo = XmlUtils.decodeBASE64(content);
					ContentInfo fromJson2 = GsonJson.getGson().fromJson(contentInfo, ContentInfo.class);
					System.out.println("fromJson2:"+fromJson2);
					// 获取 微信卡包URL
					String sp_URL = fromJson2.getSP_URL();
					// 获取 pdf下载URL
					String pdf_URL = fromJson2.getPDF_URL();
					
					inPersonal.setPdf_url(pdf_URL);
					inPersonal.setSp_url(sp_URL);
					inPersonalService.save(inPersonal);
					
					// 下载PDF 文件 参数 URL 文件 地址包含名字 比如： d:/file/1234.pdf
					String pdfPath = ApplicationListener.rootPath + ConstantStaticVariablesSet.BillingPdfPath;//暂时配到了项目的webroot的一个pdf文件夹下面
					PDFDownloader.download(pdf_URL, pdfPath+FPQQLSH+".pdf");
					responseData.setMessage(sp_URL);
					System.out.println("sp_URL is :"+sp_URL);
					responseData.setSuccess(true);
					DebugLog.logger.info("开票抬头："+inPersonal.getPName()+"   orderKey："+inPersonal.getOrderkey()+" 开票成功！");
				}else{
					responseData.setMessage(returnMessage);
					responseData.setSuccess(false);
					DebugLog.logger.error("开票抬头："+personal.getP_name()+"   orderKey："+personal.getOrderkey()+ " 开票失败！errorMessage"+returnMessage);
					//错误返回
				}
				
				// 返回 界面 进行跳转 界面
				// view.addObject("sp_url", sp_URL);
			} catch (JsonSyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else{
			responseData.setMessage("用户信息获取失败！请重试一下。");
			responseData.setSuccess(false);
			DebugLog.logger.error("用户openid为空，开票失败！");
		}
		 return GsonJson.getGson().toJson(responseData);
}
	/**
	 * 
	 * @Title: enterprise
	 * @Description: TODO 企业发票
	 * @param @param request
	 * @param @param response
	 * @param @param orderid
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value = "enterprise", produces = { "application/json;charset=UTF-8" }, method = RequestMethod.POST)
	public String enterprise(HttpServletRequest request, HttpServletResponse response, Enterprise enterprise) {
		ResponseData responseData = new ResponseData();
		
		String openid = enterprise.getOpenid();
		if(openid !=null&&!openid.equals("")){
			try {
				BillingParamtersInfo bi = BillingParamtersInfo.getbillBillingParamtersInfo();
				BillingParamters billingParamters =  new BillingParamters();
				enteprise2BillingParamters(billingParamters,enterprise);
				billingParamters.setPayway(enterprise.getPayway());
			//	billingParamters.setBZ("测试一下");
				billingParamters.setFHR(bi.getFHR());
				String FPQQLSH = "TEST"+Utils.formatToTime()+"01";
				billingParamters.setFPQQLSH(FPQQLSH);
				billingParamters.setKPLX("0");
				billingParamters.setKPR(bi.getKPR());
				billingParamters.setSKR(bi.getSKR());
				billingParamters.setXSF_DZDH(bi.getXSF_DHDZ());
				billingParamters.setZSFS("0");
				billingParamters.setXSF_NSRSBH(bi.getXSF_NSRSBH());
				billingParamters.setXSF_MC(bi.getXSF_MC()); // 测试名称不能修改
				ProductsInfo proInfo = new ProductsInfo();
				proInfo.setFPHXZ("0");
				List<ProductsInfo> prInfo = new ArrayList<ProductsInfo>();
				double total = Double.valueOf(enterprise.getAmount());
				BigDecimal bd1 = new BigDecimal(0);
				if(enterprise.getPayway().equals(ConstantStaticVariablesSet.payWay1)){
					//冰淇淋&餐饮
					proInfo.setSPBM("1010101050000000000");
					proInfo.setXMMC("餐饮");
					proInfo.setSL(ConstantStaticVariablesSet.taxRate2);
					proInfo.setXMJE(enterprise.getAmount());
					BigDecimal bigDecimal = new BigDecimal(total);
					bd1 = bigDecimal.multiply(new BigDecimal(0.06)).setScale(2,BigDecimal.ROUND_HALF_UP);
					proInfo.setSE(String.valueOf(bd1));
					prInfo.add(proInfo);
				}else if(enterprise.getPayway().equals(ConstantStaticVariablesSet.payWay2)){
					//会员卡消费
					proInfo.setSPBM("6010000000000000000");
					proInfo.setXMMC("会员卡消费");
					proInfo.setSL(ConstantStaticVariablesSet.taxRate);
					proInfo.setXMJE(enterprise.getAmount());
					BigDecimal bigDecimal = new BigDecimal(total);
					bd1 =   bigDecimal.multiply(new BigDecimal(0.00)).setScale(2,BigDecimal.ROUND_HALF_UP);
					proInfo.setSE(String.valueOf(bd1));
					prInfo.add(proInfo);
				}	
				billingParamters.setHJSE(bd1.toString());//税额
				billingParamters.setHJJE(new BigDecimal(enterprise.getAmount()).divide(new BigDecimal(bd1.toString()).setScale(2,BigDecimal.ROUND_HALF_DOWN)).toString());//金额
				billingParamters.setJSHJ(enterprise.getAmount());//价税合计
				//	billingParamters.setJSHJ(bd1.add(new BigDecimal(enterprise.getAmount())).setScale(2,BigDecimal.ROUND_HALF_UP).toString());//价税合计
				
				billingParamters.setpInfo(prInfo);
				String faPiao = Utils.getFaPiao(billingParamters);
				String fap = faPiao.substring(13, faPiao.length()-1);
				Interface fromJson = GsonJson.getGson().fromJson(fap, Interface.class);
				String content = fromJson.getData().getContent();
				String returnCode = fromJson.getReturnStateInfo().getReturnCode();
				String returnMessage = fromJson.getReturnStateInfo().getReturnMessage();
				System.out.println(content);
				if(returnCode.equals("0000")){
					InEnterprise inEnterprise = new InEnterprise();
					inEnterprise.setAmount(billingParamters.getJSHJ());
					inEnterprise.setCreatetime(new Date());
					inEnterprise.setEAccount(enterprise.getE_account());
					inEnterprise.setEAddress(enterprise.getE_address());
					inEnterprise.setEBank(enterprise.getE_bank());
					inEnterprise.setENumber(enterprise.getE_number());
					inEnterprise.setEPhone(enterprise.getE_phone());
					inEnterprise.setOpenid(enterprise.getOpenid());
					inEnterprise.setOrderid(enterprise.getOrderid());
					inEnterprise.setOrderkey(enterprise.getOrderkey());
					inEnterprise.setPEmail(enterprise.getE_email());
					inEnterprise.setPMphone(enterprise.getE_mphone());
					inEnterprise.setPName(enterprise.getE_name());
					inEnterprise.setPayWay(enterprise.getPayway());
					inEnterprise.setNsrsbh(billingParamters.getGMF_NSRSBH());
					inEnterprise.setTotalFee(billingParamters.getHJJE());
					inEnterprise.setSE(billingParamters.getHJSE());
					inEnterprise.setSL(enterprise.getPayway().equals(ConstantStaticVariablesSet.payWay1)?"0.06":"0");
					
					// 把content 64解密
					String contentInfo = XmlUtils.decodeBASE64(content);
					ContentInfo fromJson2 = GsonJson.getGson().fromJson(contentInfo, ContentInfo.class);
					System.out.println(fromJson2);
					// 获取 微信卡包URL
					String sp_URL = fromJson2.getSP_URL();
					// 获取 pdf下载URL
					String pdf_URL = fromJson2.getPDF_URL();
					
					inEnterprise.setPdf_URL(pdf_URL);
					inEnterprise.setSp_URL(sp_URL);
					inEnterpriseService.save(inEnterprise);
					// 下载PDF 文件 参数 URL 文件 地址包含名字 比如： d:/file/1234.pdf
					String pdfPath = ApplicationListener.rootPath + ConstantStaticVariablesSet.BillingPdfPath;//暂时配到了项目的webroot的一个pdf文件夹下面
					PDFDownloader.download(pdf_URL, pdfPath+FPQQLSH+".pdf");
					responseData.setMessage(sp_URL);
					responseData.setSuccess(true);
					DebugLog.logger.info("发票抬头： "+inEnterprise.getPName()+" orderKey："+inEnterprise.getOrderkey()+" 开票成功！");
				}else{
					responseData.setMessage(returnMessage);
					responseData.setSuccess(false);
					DebugLog.logger.error("发票抬头： "+enterprise.getE_name()+":orderKey为"+enterprise.getOrderkey()+"开票失败！"+"errorMessage:"+returnMessage);
					//错误返回
				}
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonSyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			responseData.setMessage("用户信息获取失败！请重试一下。");
			responseData.setSuccess(false);
			DebugLog.logger.error("用户openid获取失败！");
		}
		return GsonJson.getGson().toJson(responseData);
	}

	@RequestMapping(value = "error")
	public ModelAndView getError(){
		ModelAndView view = new ModelAndView();
		view.setViewName("error");
		return view;
	}
	@RequestMapping(value = "cx")
	public ModelAndView getCX() {
		ModelAndView view = new ModelAndView();
		view.setViewName("error");// 错误界面

		view.addObject("openid", "orderid");
		// 正确界面
		view.setViewName("index");
		// 根据orderkey 获取 订单信息
		String orderkey = "101001";
		// 订单金额
		view.addObject("amount", "￥111.00");
		// 订单ID
		view.addObject("orderid", "100983");
		// 时间
		view.addObject("date", "2017-8-25 15:43:05");

		return view;
	}
	
	@Resource
	private OrderPaymentDao orderPaymentDao;
	@ResponseBody
	@RequestMapping(value = "personal2", produces = { "application/json;charset=UTF-8" }, method = RequestMethod.POST)
	public String personal2(HttpServletRequest request, HttpServletResponse response, Personal personal) {
		//ModelAndView view = new ModelAndView();
		
		ResponseData responseData = new ResponseData();
		String storeId = personal.getStoreId();
		String orderkey = personal.getOrderkey();
		String posId = personal.getPosId();
		String amount = personal.getAmount();
		String openid = personal.getOpenid();
				
		Double money = 0.00;
		List<InPersonal> findBy = inPersonalService.findBy("orderkey", orderkey);
		if(findBy == null || findBy.size() == 0){//  没有开过  个人发票
			List<InEnterprise> findBy2 = inEnterpriseService.findBy("orderkey", orderkey);
			if(findBy2 == null || findBy2.size() == 0){ // 没有开过  企业发票
				//会员  包含支付方式
				List<MemberPayment> listMemberPayment = memberPaymentService.getAll();
				//正常产品  包含支付方式
				List<PaymentWay> listPaymentWay = paymentWayService.getAll();
				// 无法  开发票的   门店 列表
				List<StoreList> listStoreList = storeListService.getAll();
				
				DatabaseContextHolder.setCustomerType(DatabaseContextHolder.SESSION_FACTORY_BO);
				// 返回一个开票结果，价格，支付方式
				BillingResult invoice = posOrderService.getInvoice(orderkey,listMemberPayment,listPaymentWay,listStoreList);
				// 当前项目数据库
				DatabaseContextHolder.setCustomerType(DatabaseContextHolder.SESSION_FACTORY_INVOICE);
				
				List<OrderPayment> orderPayment = orderPaymentDao.findBy("orderKey",orderkey);
				Double total1 = 0.00;
				for(int j = 0 ; j < orderPayment.size(); j ++){
					total1+=orderPayment.get(j).getPayValue()+orderPayment.get(j).getOverCharged();
				}
				
				List<PosOrder> posOrder = posOrderService.findBy("orderkey", orderkey);
				for(PosOrder pos:posOrder){
					//money += pos.get;
				}
				if(invoice.getTotalFee()!=null){
					
					//List<InPersonal> inPersonal = inPersonalService.findBy("openid", openid);
					money = invoice.getTotalFee();
					}
				if(amount.equals(money.toString())){
					if(openid !=null&&!openid.equals("")){
						// 调用接口开发票
						try {
							
							BillingParamtersInfo bi = BillingParamtersInfo.getbillBillingParamtersInfo();
							BillingParamters billingParamters =  new BillingParamters();
							personal2BillingParamters(personal, billingParamters);
							billingParamters.setPayway(personal.getPayway());
							//billingParamters.setBZ("测试一下");
							billingParamters.setFHR(bi.getFHR());
							String FPQQLSH = "TEST"+Utils.formatToTime()+"01";
							billingParamters.setFPQQLSH(FPQQLSH);
							
							billingParamters.setKPLX("0");
							billingParamters.setKPR(bi.getKPR());
							billingParamters.setSKR(bi.getSKR());
							billingParamters.setZSFS("0");
//							billingParamters.setXSF_YHZH("农业银行、6228480048");
							billingParamters.setXSF_NSRSBH(bi.getXSF_NSRSBH());
							billingParamters.setXSF_MC(bi.getXSF_MC()); // 测试名称不能修改
							billingParamters.setXSF_DZDH(bi.getXSF_DHDZ());
							ProductsInfo proInfo = new ProductsInfo();
							List<ProductsInfo> prInfo = new ArrayList<ProductsInfo>();
							proInfo.setFPHXZ("0");
							double total = Double.valueOf(personal.getAmount());
							BigDecimal bd1 = new BigDecimal(0);
							if(invoice.getPayWay().equals(ConstantStaticVariablesSet.payWay1)){
								//冰淇淋&餐饮
								proInfo.setSPBM("1010101050000000000");
								proInfo.setXMMC("餐饮");
								proInfo.setSL("0.06");
								proInfo.setXMJE(personal.getAmount());//String.valueOf(total)
								BigDecimal bigDecimal = new BigDecimal(total);
								bd1 = bigDecimal.multiply(new BigDecimal(0.06)).setScale(2,BigDecimal.ROUND_HALF_UP);
								proInfo.setSE(String.valueOf(bd1));
								prInfo.add(proInfo);
							}else if(invoice.getPayWay().equals(ConstantStaticVariablesSet.payWay2) ){
								//会员卡消费
								proInfo.setSPBM("6010000000000000000");
								proInfo.setXMMC("会员卡消费");
								proInfo.setSL("0");
								proInfo.setXMJE(personal.getAmount());
								BigDecimal bigDecimal = new BigDecimal(total);
								bd1 =   bigDecimal.multiply(new BigDecimal(0.00)).setScale(2,BigDecimal.ROUND_HALF_UP);
								proInfo.setSE(String.valueOf(bd1));
								prInfo.add(proInfo);
							}	
							billingParamters.setHJSE(bd1.toString());
							billingParamters.setHJJE(personal.getAmount());
							billingParamters.setJSHJ(bd1.add(new BigDecimal(personal.getAmount())).setScale(2,BigDecimal.ROUND_HALF_UP).toString());
							billingParamters.setpInfo(prInfo);
							String faPiao = Utils.getFaPiao(billingParamters);
							String fap = faPiao.substring(13, faPiao.length()-1);
							Interface fromJson = GsonJson.getGson().fromJson(fap, Interface.class);
							String content = fromJson.getData().getContent();
							String returnCode = fromJson.getReturnStateInfo().getReturnCode();
							String returnMessage = fromJson.getReturnStateInfo().getReturnMessage();
							System.out.println(content);
							if(returnCode.equals("0000")){
								//正确返回
								InPersonal inPersonal = new InPersonal();
								inPersonal.setCreatetime(new Date());
								inPersonal.setAmount(personal.getAmount());
								inPersonal.setOrderid(personal.getOrderid());
								inPersonal.setOpenid(personal.getOpenid());
								inPersonal.setOrderkey(personal.getOrderkey());
								inPersonal.setPEmail(personal.getP_email());
								inPersonal.setPMphone(personal.getP_mphone());
								inPersonal.setUpdatetime(new Date());
								inPersonal.setPName(personal.getP_name());
								
								//System.out.println(inPersonalService.save(inPersonal));
								
								// 把content 64解密
								String contentInfo = XmlUtils.decodeBASE64(content);
								ContentInfo fromJson2 = GsonJson.getGson().fromJson(contentInfo, ContentInfo.class);
								System.out.println("fromJson2:"+fromJson2);
								// 获取 微信卡包URL
								String sp_URL = fromJson2.getSP_URL();
								// 获取 pdf下载URL
								String pdf_URL = fromJson2.getPDF_URL();
								
								inPersonal.setPdf_url(pdf_URL);
								inPersonal.setSp_url(sp_URL);
								inPersonalService.save(inPersonal);
								
								// 下载PDF 文件 参数 URL 文件 地址包含名字 比如： d:/file/1234.pdf
								String pdfPath = ApplicationListener.rootPath + ConstantStaticVariablesSet.BillingPdfPath;//暂时配到了项目的webroot的一个pdf文件夹下面
								PDFDownloader.download(pdf_URL, pdfPath+FPQQLSH+".pdf");
								responseData.setMessage(sp_URL);
								System.out.println("sp_URL is :"+sp_URL);
								responseData.setSuccess(true);
								DebugLog.logger.info("开票抬头："+inPersonal.getPName()+"   orderKey："+inPersonal.getOrderkey()+" 开票成功！");
							}else{
								DebugLog.logger.error("开票抬头："+personal.getP_name()+"   orderKey："+personal.getOrderkey()+ " 开票失败！errorMessage"+returnMessage);
								//错误返回
							}
							
							// 返回 界面 进行跳转 界面
							// view.addObject("sp_url", sp_URL);
						} catch (JsonSyntaxException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
				}else{
					responseData.setMessage("您输入的订单号和金额不匹配,请重新输入！");
					String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxa2ec4046c12f1c84&redirect_uri=http://wechat.talentsolution.net/Iceason_Invoice/weixin/error.do&response_type=pos&scope=snsapi_base&state=1#wechat_redirect";
				//	String url = "http://wechat.talentsolution.net/Iceason_Invoice/weixin/error.do?state=1";
					responseData.setMessage(url);
					responseData.setSuccess(false);
				}
			}else{
				responseData.setSuccess(true);
				responseData.setMessage(findBy2.get(0).getSp_URL());
			}
			
		}else{
			responseData.setSuccess(true);
			responseData.setMessage(findBy.get(0).getSp_url());
		}
		
		return JSONutilss.toJSONString(responseData);
	}
	
	@ResponseBody
	@RequestMapping(value = "enterprise2", produces = { "application/json;charset=UTF-8" }, method = RequestMethod.POST)
	public String enterprise2(HttpServletRequest request, HttpServletResponse response, Enterprise enterprise) {
		ModelAndView view = new ModelAndView();
		ResponseData responseData = new ResponseData();
		String storeId = enterprise.getStoreId();
		String orderkey = enterprise.getOrderkey();
		String posId = enterprise.getPosId();
		String amount = enterprise.getAmount();
		String openid = enterprise.getOpenid();
		
		UrlInfo urlInfo = UrlInfo.getUrlInfo();
		
		Double money = 0.00;
		List<InPersonal> findBy = inPersonalService.findBy("orderkey", orderkey);
		if(findBy == null || findBy.size() == 0){//  没有开过  个人发票  
			List<InEnterprise> findBy2 = inEnterpriseService.findBy("orderkey", orderkey);
			if(findBy2 == null || findBy2.size() == 0){ // 没有开过  企业发票
				List<MemberPayment> listMemberPayment = memberPaymentService.getAll();
				List<PaymentWay> listPaymentWay = paymentWayService.getAll();
				List<StoreList> listStoreList = storeListService.getAll();
				//DatabaseContextHolder.setCustomerType(DatabaseContextHolder.SESSION_FACTORY_BO);
				BillingResult invoice = posOrderService.getInvoice(orderkey,listMemberPayment,listPaymentWay,listStoreList);
				// 自己的 数据库
			//	DatabaseContextHolder.setCustomerType(DatabaseContextHolder.SESSION_FACTORY_INVOICE);
				if(invoice!=null){
					money = invoice.getTotalFee();
				}
				if(amount.equals(money.toString())){
					if(openid !=null&&!openid.equals("")){
						try {
							BillingParamtersInfo bi = BillingParamtersInfo.getbillBillingParamtersInfo();
							BillingParamters billingParamters =  new BillingParamters();
							enteprise2BillingParamters(billingParamters,enterprise);
							billingParamters.setPayway(enterprise.getPayway());
						//	billingParamters.setBZ("测试一下");
							billingParamters.setFHR(bi.getFHR());
							String FPQQLSH = "TEST"+Utils.formatToTime()+"01";
							billingParamters.setFPQQLSH(FPQQLSH);
							billingParamters.setKPLX("0");
							billingParamters.setKPR(bi.getKPR());
							billingParamters.setSKR(bi.getSKR());
							billingParamters.setXSF_DZDH(bi.getXSF_DHDZ());
							billingParamters.setZSFS("0");
							billingParamters.setXSF_NSRSBH(bi.getXSF_NSRSBH());
							billingParamters.setXSF_MC(bi.getXSF_MC()); // 测试名称不能修改
							ProductsInfo proInfo = new ProductsInfo();
							proInfo.setFPHXZ("0");
							List<ProductsInfo> prInfo = new ArrayList<ProductsInfo>();
							double total = Double.valueOf(enterprise.getAmount());
							BigDecimal bd1 = new BigDecimal(0);
							if(invoice.getPayWay().equals(ConstantStaticVariablesSet.payWay1)){
								//冰淇淋&餐饮
								proInfo.setSPBM("1010101050000000000");
								proInfo.setXMMC("餐饮");
								proInfo.setSL(ConstantStaticVariablesSet.taxRate2);
								proInfo.setXMJE(enterprise.getAmount());
								BigDecimal bigDecimal = new BigDecimal(total);
								bd1 = bigDecimal.multiply(new BigDecimal(0.06)).setScale(2,BigDecimal.ROUND_HALF_UP);
								proInfo.setSE(String.valueOf(bd1));
								prInfo.add(proInfo);
							}else if(invoice.getPayWay().equals(ConstantStaticVariablesSet.payWay2)){
								//会员卡消费
								proInfo.setSPBM("6010000000000000000");
								proInfo.setXMMC("会员卡消费");
								proInfo.setSL(ConstantStaticVariablesSet.taxRate);
								proInfo.setXMJE(enterprise.getAmount());
								BigDecimal bigDecimal = new BigDecimal(total);
								bd1 =   bigDecimal.multiply(new BigDecimal(0.00)).setScale(2,BigDecimal.ROUND_HALF_UP);
								proInfo.setSE(String.valueOf(bd1));
								prInfo.add(proInfo);
							}	
							billingParamters.setHJSE(bd1.toString());
							billingParamters.setHJJE(enterprise.getAmount());
							billingParamters.setJSHJ(bd1.add(new BigDecimal(enterprise.getAmount())).setScale(2,BigDecimal.ROUND_HALF_UP).toString());
							billingParamters.setpInfo(prInfo);
							String faPiao = Utils.getFaPiao(billingParamters);
							String fap = faPiao.substring(13, faPiao.length()-1);
							Interface fromJson = GsonJson.getGson().fromJson(fap, Interface.class);
							String content = fromJson.getData().getContent();
							String returnCode = fromJson.getReturnStateInfo().getReturnCode();
							String returnMessage = fromJson.getReturnStateInfo().getReturnMessage();
							System.out.println(content);
							if(returnCode.equals("0000")){
								InEnterprise inEnterprise = new InEnterprise();
								inEnterprise.setAmount(billingParamters.getJSHJ());
								inEnterprise.setCreatetime(new Date());
								inEnterprise.setEAccount(enterprise.getE_account());
								inEnterprise.setEAddress(enterprise.getE_address());
								inEnterprise.setEBank(enterprise.getE_bank());
								inEnterprise.setENumber(enterprise.getE_number());
								inEnterprise.setEPhone(enterprise.getE_phone());
								inEnterprise.setOpenid(enterprise.getOpenid());
								inEnterprise.setOrderid(enterprise.getOrderid());
								inEnterprise.setOrderkey(enterprise.getOrderkey());
								inEnterprise.setPEmail(enterprise.getE_email());
								inEnterprise.setPMphone(enterprise.getE_mphone());
								inEnterprise.setPName(enterprise.getE_name());
								inEnterprise.setPayWay(enterprise.getPayway());
								inEnterprise.setNsrsbh(billingParamters.getGMF_NSRSBH());
								inEnterprise.setTotalFee(billingParamters.getHJJE());
								inEnterprise.setSE(billingParamters.getHJSE());
								inEnterprise.setSL(enterprise.getPayway().equals(ConstantStaticVariablesSet.payWay1)?"0.06":"0");
								
								// 把content 64解密
								String contentInfo = XmlUtils.decodeBASE64(content);
								ContentInfo fromJson2 = GsonJson.getGson().fromJson(contentInfo, ContentInfo.class);
								System.out.println(fromJson2);
								// 获取 微信卡包URL
								String sp_URL = fromJson2.getSP_URL();
								// 获取 pdf下载URL
								String pdf_URL = fromJson2.getPDF_URL();
								
								inEnterprise.setPdf_URL(pdf_URL);
								inEnterprise.setSp_URL(sp_URL);
								inEnterpriseService.save(inEnterprise);
								// 下载PDF 文件 参数 URL 文件 地址包含名字 比如： d:/file/1234.pdf
								String pdfPath = ApplicationListener.rootPath + ConstantStaticVariablesSet.BillingPdfPath;//暂时配到了项目的webroot的一个pdf文件夹下面
								PDFDownloader.download(pdf_URL, pdfPath+FPQQLSH+".pdf");
								responseData.setMessage(sp_URL);
								responseData.setSuccess(true);
								view.setViewName(sp_URL);
								DebugLog.logger.info("发票抬头： "+inEnterprise.getPName()+" orderKey："+inEnterprise.getOrderkey()+" 开票成功！");
							}else{
								responseData.setMessage(returnMessage);
								responseData.setSuccess(false);
								view.setViewName("error");
								view.addObject("errorMessage", responseData);
								DebugLog.logger.error("发票抬头： "+enterprise.getE_name()+":orderKey为"+enterprise.getOrderkey()+"开票失败！"+"errorMessage:"+returnMessage);
								//错误返回
							}
						} catch (NumberFormatException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (JsonSyntaxException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}else{
				//	String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxa2ec4046c12f1c84&redirect_uri=http://wechat.talentsolution.net/Iceason_Invoice/weixin/error.do&response_type=pos&scope=snsapi_base&state=1#wechat_redirect";
					String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+urlInfo.getAppId()+"&redirect_uri="+urlInfo.getRedirectedUrl()+"/Iceason_Invoice/weixin/error.do&response_type=pos&scope=snsapi_base&state=1#wechat_redirect";
				//	String url = "https://";
					responseData.setMessage(url);
					responseData.setSuccess(false);
				}
			}else{
				String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxa2ec4046c12f1c84&redirect_uri=http://wechat.talentsolution.net/Iceason_Invoice/weixin/error.do&response_type=pos&scope=snsapi_base&state=2#wechat_redirect";
				//	String url = "https://";
					responseData.setMessage(url);
					responseData.setSuccess(false);
			}
			
		}else{
			String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxa2ec4046c12f1c84&redirect_uri=http://wechat.talentsolution.net/Iceason_Invoice/weixin/error.do&response_type=pos&scope=snsapi_base&state=2#wechat_redirect";
			//	String url = "https://";
				responseData.setMessage(url);
				responseData.setSuccess(false);
		}
		
		return JSONutilss.toJSONString(responseData);
	}
	@RequestMapping(value="error", produces = { "application/json;charset=UTF-8" }, method = RequestMethod.POST)
	public ModelAndView goerror(AccessTokenURL accessTokenURL){
		ModelAndView view = new ModelAndView();
		//String message=accessTokenURL.getState();
		ResponseData responseData = new ResponseData();
		responseData.setMessage("订单号和金额不符合，请重新输入");
		view.setViewName("error");
		view.addObject("errorMessage", responseData);
		return view;
	}
	/**
	 * 生成订单时将信息转换，并签名
	 */
	/*
	 * private WeChatPayDatajs executeRequestPayInfo(String appid,Ticket
	 * ticket,String url) { WeChatHttpsRequestUtil httpsRequestUtil = new
	 * WeChatHttpsRequestUtil(); String nonceStr =
	 * httpsRequestUtil.getRandomStringByLength(32); String timeStamp =
	 * String.valueOf(System.currentTimeMillis()).substring(0, 10);
	 * ArrayList<String> list = new ArrayList<String>(); if (ticket!=null) {
	 * list.add("jsapi_ticket=" + ticket.getTicket() + "&");
	 * list.add("noncestr=" + nonceStr + "&"); list.add("timestamp=" + timeStamp
	 * + "&"); list.add("url=" + url + "&"); } // 生成sign String sign =
	 * httpsRequestUtil.generateSignByListjs(list); WeChatPayDatajs
	 * chatPayDatajs = new WeChatPayDatajs(appid, timeStamp, nonceStr, sign);
	 * return chatPayDatajs; }
	 */
	/** 
	 * 将personal中的信息保存到BillingParamets中去
	 * 个人
	 * */
	public BillingParamters personal2BillingParamters(Personal personal,BillingParamters billingParamters){
		
		//personal.getPayway();
		//billingParamters.setHJJE(personal.getAmount());
		billingParamters.setGMF_MC(personal.getP_name());
		//billingParamters.setHJSE(personal.getPayway().equals("100") ? new BigDecimal(personal.getAmount()).toString() :"0");
		//billingParamters.setJSHJ((new BigDecimal(billingParamters.getHJSE()+new BigDecimal(billingParamters.getHJJE())).toString()));;
		return billingParamters;
	}
	
	/**
	 * 企业使用
	 * **/
	public BillingParamters enteprise2BillingParamters(BillingParamters billingParamters,Enterprise enterprise){
		billingParamters.setGMF_MC(enterprise.getE_name());
		billingParamters.setGMF_DZDH(enterprise.getE_address()+"、"+enterprise.getE_phone());
		billingParamters.setGMF_YHZH(enterprise.getE_bank()+"、"+enterprise.getE_account());
		billingParamters.setGMF_NSRSBH(enterprise.getE_number());
		billingParamters.setGMF_DZYX(enterprise.getE_email());
		billingParamters.setGMF_SJH(enterprise.getE_mphone());
		return billingParamters;
	}
	
	@RequestMapping(value="subway")
	public ModelAndView subway(AccessTokenURL accessTokenURL,@RequestParam("state") String state){
		//取scanResult
		ModelAndView view = new ModelAndView();
		ResponseData responseData = new ResponseData();
		//String state = accessTokenURL.getState();
		
		if(state.equals("1")||state=="1"){
			responseData.setMessage("您输入的订单号和金额不匹配,请重新输入！");
			
		}else if(state.equals("2")||state=="2"){
			responseData.setMessage("此订单已开具发票！");
		}
		view.addObject("errorMessage", responseData);
		view.setViewName("error");
		//view.setViewName("jsp/qrCode");
		return view;
	}
}
