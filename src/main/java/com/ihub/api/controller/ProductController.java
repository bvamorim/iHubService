package com.ihub.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ihub.api.model.domainobject.Product;
import com.ihub.api.services.IProductService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * <h1>ProductController</h1>
 * 
 * @author Bruno Amorim
 * @version 1.0
 * @since 2021-05-29
 */
@RestController
@RequestMapping("/product")
@Api(tags = { "Product API" })
public class ProductController {

	@Autowired
	private IProductService iProductService;

	/**
	 * Method of listing products
	 * 
	 * @return iProductoService.findAll()
	 */
	@ApiOperation(value = "Returns a List of all existing products")
	@RequestMapping(value = "/list", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Product> list() {

		return iProductService.findAll();
	}

	/**
	 * Method to search for a product with id
	 * 
	 * @param id
	 * @return iProductService.findById(id)
	 * @throws Exception
	 */
	@ApiOperation(value = "Returns a Product found by ID")
	@GetMapping("/find/{id}")
	public Product searchProductById(@PathVariable Long id) {
		Product product = iProductService.findById(id);

		return product;
	}

	/**
	 * Method to save a product
	 * 
	 * @param product
	 * @return
	 */
	@ApiOperation(value = "Add a new Product")
	@PostMapping("/add")
	@ResponseStatus(HttpStatus.CREATED)
	public Product saveProduct(@Valid @RequestBody Product product) {

		Product savedProduct = iProductService.saveProduct(product);
		return savedProduct;
	}

	/**
	 * Method for update a product by id
	 * 
	 * @param ProductDto
	 * @param id
	 * @return updatedProduct
	 */
	@ApiOperation(value = "Update an existing Product found by ID")
	@PatchMapping("/update/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Product updateProduct(@RequestBody Product product, @PathVariable Long id) {

		Product productTemp = iProductService.findById(id);
		productTemp.setName(product.getName());
		productTemp.setPrice(product.getPrice());

		Product updatedProduct = iProductService.saveProduct(productTemp);
		return updatedProduct;

	}

	/**
	 * Method for delete a product by id
	 * 
	 * @param id
	 */
	@ApiOperation(value = "Deletes an existing Product found by ID")
	@DeleteMapping("/delete/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteProduct(@PathVariable Long id) {
		iProductService.deleteById(id);

	}

}
