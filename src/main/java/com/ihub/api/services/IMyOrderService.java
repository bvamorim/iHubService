package com.ihub.api.services;

import java.util.List;

import com.ihub.api.model.datatransferobject.PlaceOrderDto;
import com.ihub.api.model.domainobject.MyOrder;

/**
 * <h1>IMyOrderService</h1>
 * 
 * @author Bruno Amorim
 * @version 1.0
 * @since 2021-05-29
 */
public interface IMyOrderService {

	public void placeOrder(Long userId);

	public Long saveOrder(PlaceOrderDto placeOrderDto, Long userId);

	public MyOrder getOrderFromDto(PlaceOrderDto placeOrderDto, Long userId);

	public List<MyOrder> listOrders(Long userId);

}
