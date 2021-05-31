package com.ihub.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ihub.api.dao.OrderItemsDao;
import com.ihub.api.model.domainobject.OrderItem;

/**
 * <h1>OrderItemsService</h1>
 * 
 * @author Bruno Amorim
 * @version 1.0
 * @since 2021-05-30
 */
@Service
@Transactional
public class OrderItemsService implements IOrderItemsService {

	@Autowired
	private OrderItemsDao orderItemsDao;

	public void addOrderedProducts(OrderItem orderItem) {
		orderItemsDao.save(orderItem);
	}

}