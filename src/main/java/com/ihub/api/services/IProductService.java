package com.ihub.api.services;

import java.util.List;

import com.ihub.api.model.domainobject.Product;

/**
 * <h1>IProductService</h1>
 * 
 * @author Bruno Amorim
 * @version 1.0
 * @since 2021-05-29
 */
public interface IProductService {

	List<Product> findAll();

	Product findById(Long id);

	void deleteById(Long id);

	Product saveProduct(Product product);

}
