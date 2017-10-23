package invoice.iceason.isposbo.service;

import invoice.iceason.isposbo.dao.OrderElementItemDao;
import invoice.iceason.isposbo.entity.OrderElementItem;
import invoice.iceason.weixin.service.BaseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderElementItemService extends BaseService<OrderElementItem>{

	private OrderElementItemDao orderElementItemDao;
	
	@Autowired
	public OrderElementItemService(OrderElementItemDao orderElementItemDao){
		super(orderElementItemDao);
		this.orderElementItemDao = orderElementItemDao;
	}


}
