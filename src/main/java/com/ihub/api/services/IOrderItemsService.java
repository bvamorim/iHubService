package com.ihub.api.services;

import com.ihub.api.model.domainobject.OrderItem;

/**
 * <h1>IOrderItemsService</h1>
 * 
 * @author Bruno Amorim
 * @version 1.0
 * @since 2021-05-30
 */
public interface IOrderItemsService {

	public void addOrderedProducts(OrderItem orderItem);

}
