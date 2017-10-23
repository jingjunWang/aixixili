package invoice.iceason.isposbo.service;

import invoice.iceason.isposbo.dao.CancelOrderDao;
import invoice.iceason.isposbo.entity.CancelOrder;
import invoice.iceason.weixin.service.BaseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CancelOrderService extends BaseService<CancelOrder>{

	private CancelOrderDao cancelOrderDao;
	
	@Autowired
	public CancelOrderService(CancelOrderDao cancelOrderDao){
		super(cancelOrderDao);
		this.cancelOrderDao = cancelOrderDao;
	}


}
