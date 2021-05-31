package com.ihub.api.services;

import java.time.LocalDateTime;
import java.util.List;

import com.ihub.api.exception.OrderNotFoundException;
import com.ihub.api.model.datatransferobject.PlaceOrderDto;
import com.ihub.api.model.domainobject.MyOrder;
import com.ihub.api.model.domainobject.User;

/**
 * <h1>IOrderService</h1>
 * 
 * @author Bruno Amorim
 * @version 1.0
 * @since 2021-05-29
 */
public interface IOrderService {

	public MyOrder saveOrder(PlaceOrderDto orderDto, User user);

	public MyOrder getOrderFromDto(PlaceOrderDto orderDto, User user);

	public List<MyOrder> listOrders(User user);
	
	public List<MyOrder> listOrdersBetween(LocalDateTime initDate, LocalDateTime finalDate);

	public MyOrder getMyOrder(Long orderId);

	public void placeOrder(User user) throws OrderNotFoundException;

}
