package com.ihub.api.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ihub.api.model.domainobject.Product;

/**
 * <h1>ProductDao</h1>
 * 
 * @author Bruno Amorim
 * @version 1.0
 * @since 2021-05-29
 */
@Repository
public interface ProductDao extends CrudRepository<Product, Long> {
}