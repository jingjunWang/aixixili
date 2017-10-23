package invoice.iceason.isposbo.service;

import invoice.iceason.isposbo.dao.OrderItemDao;
import invoice.iceason.isposbo.entity.OrderItem;
import invoice.iceason.weixin.service.BaseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderItemService extends BaseService<OrderItem>{

	private OrderItemDao orderItemDao;
	
	@Autowired
	public OrderItemService(OrderItemDao orderItemDao){
		super(orderItemDao);
		this.orderItemDao = orderItemDao;
	}


}
