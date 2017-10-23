package invoice.iceason.isposbo.service;

import invoice.iceason.isposbo.dao.RefundOrderDao;
import invoice.iceason.isposbo.entity.RefundOrder;
import invoice.iceason.weixin.service.BaseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RefundOrderService extends BaseService<RefundOrder>{

	private RefundOrderDao refundOrderDao;
	
	@Autowired
	public RefundOrderService(RefundOrderDao refundOrderDao){
		super(refundOrderDao);
		this.refundOrderDao = refundOrderDao;
	}


}
