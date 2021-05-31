package com.ihub.api.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ihub.api.model.domainobject.OrderItem;

/**
 * <h1>OrderItemsDao</h1>
 * 
 * @author Bruno Amorim
 * @version 1.0
 * @since 2021-05-30
 */
@Repository
public interface OrderItemsDao extends CrudRepository<OrderItem, Long> {
}