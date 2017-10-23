package invoice.iceason.isposbo.service;

import invoice.iceason.isposbo.dao.OrderDiscountDao;
import invoice.iceason.isposbo.entity.OrderDiscount;
import invoice.iceason.weixin.service.BaseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderDiscountService extends BaseService<OrderDiscount>{

	private OrderDiscountDao orderDiscountDao;
	
	@Autowired
	public OrderDiscountService(OrderDiscountDao orderDiscountDao){
		super(orderDiscountDao);
		this.orderDiscountDao = orderDiscountDao;
	}


}
