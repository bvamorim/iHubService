package com.ihub.api.services;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ihub.api.dao.ProductDao;
import com.ihub.api.model.domainobject.Product;

/**
 * <h1>ProductService</h1>
 * 
 * @author Bruno Amorim
 * @version 1.0
 * @since 2021-05-29
 */
@Service
public class ProductService implements IProductService {

	@Autowired
	private ProductDao productDao;

	public ProductService() throws IOException {
		super();
	}

	/**
	 * Method in charge of listing all products
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Product> findAll() {

		return (List<Product>) productDao.findAll();
	}

	/**
	 * Method of searching for product by id
	 */
	@Override
	@Transactional(readOnly = true)
	public Product findById(Long id) {

		return productDao.findById(id).orElse(null);
	}

	/**
	 * Method save product
	 */
	@Override
	@Transactional
	public Product saveProduct(Product product) {

		return productDao.save(product);
	}

	/**
	 * Method delete for id product
	 */
	@Override
	@Transactional
	public void deleteById(Long id) {
		productDao.deleteById(id);
	}

}
