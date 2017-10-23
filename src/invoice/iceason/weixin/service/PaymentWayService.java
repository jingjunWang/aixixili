package invoice.iceason.weixin.service;

import invoice.iceason.weixin.dao.PaymentWayDao;
import invoice.iceason.weixin.entity.PaymentWay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentWayService extends BaseService<PaymentWay>{

	private PaymentWayDao paymentWayDao;
	
	@Autowired
	public PaymentWayService(PaymentWayDao paymentWayDao){
		super(paymentWayDao);
		this.paymentWayDao = paymentWayDao;
	}


}
