package invoice.iceason.isposbo.service;

import invoice.iceason.isposbo.dao.OrderPaymentDao;
import invoice.iceason.isposbo.entity.OrderPayment;
import invoice.iceason.weixin.service.BaseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderPaymentService extends BaseService<OrderPayment>{

	private OrderPaymentDao orderPaymentDao;
	
	@Autowired
	public OrderPaymentService(OrderPaymentDao orderPaymentDao){
		super(orderPaymentDao);
		this.orderPaymentDao = orderPaymentDao;
	}


}
