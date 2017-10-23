package invoice.iceason.weixin.service;

import invoice.iceason.weixin.dao.MemberPaymentDao;
import invoice.iceason.weixin.entity.MemberPayment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberPaymentService extends BaseService<MemberPayment>{

	private MemberPaymentDao memberPaymentDao;
	
	@Autowired
	public MemberPaymentService(MemberPaymentDao memberPaymentDao){
		super(memberPaymentDao);
		this.memberPaymentDao = memberPaymentDao;
	}


}
