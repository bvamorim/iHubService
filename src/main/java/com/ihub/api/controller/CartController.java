package com.ihub.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ihub.api.exception.CartItemNotExistException;
import com.ihub.api.exception.ProductNotExistException;
import com.ihub.api.model.datatransferobject.AddToCartDto;
import com.ihub.api.model.datatransferobject.CartDto;
import com.ihub.api.model.domainobject.Cart;
import com.ihub.api.model.domainobject.Product;
import com.ihub.api.services.ICartService;
import com.ihub.api.services.IProductService;
import com.ihub.api.services.IUserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

/**
 * <h1>CartController</h1>
 * 
 * @author Bruno Amorim
 * @version 1.0
 * @since 2021-05-30
 */
@RestController
@RequestMapping("/cart")
@Api(tags = { "Cart API" })
public class CartController {

	@Autowired
	private ICartService iCartService;

	@Autowired
	private IProductService iProductService;

	@Autowired
	private IUserService iUserService;

	@ApiOperation(value = "Add a product/quantity to the logged-in users cart")
	@PostMapping("/add")
	public Cart addToCart(@RequestBody AddToCartDto addToCartDto) throws ProductNotExistException {

		Product product = iProductService.findById(addToCartDto.getProductId());

		Cart cartDto = iCartService.addToCart(addToCartDto, product, iUserService.getLoggedUser());
		return cartDto;

	}

	@ApiOperation(value = "Lists the products in the cart for the logged-in user")
	@GetMapping("/list")
	public CartDto list() {
		Long user = iUserService.getLoggedUser().getId();
		CartDto cartDto = iCartService.listCartItems(user);
		return cartDto;

	}

	@ApiOperation(value = "Update a product/quantity to the logged-in users cart")
	@PutMapping("/update/{cartItemId}")
	public ResponseEntity<ApiResponse> updateCartItem(@RequestBody @Valid AddToCartDto cartDto)
			throws ProductNotExistException {

		Product product = iProductService.findById(cartDto.getProductId());
		iCartService.updateCartItem(cartDto, iUserService.getLoggedUser(), product);

		// "Product has been updated"
		return new ResponseEntity<ApiResponse>(HttpStatus.OK);
	}

	@ApiOperation(value = "Delete an existing Item found by ID from the logged-in users cart")
	@DeleteMapping("/delete/{cartItemId}")
	public ResponseEntity<ApiResponse> deleteCartItem(@PathVariable("cartItemId") Long itemID)
			throws CartItemNotExistException {

		iCartService.deleteCartItem(itemID);

		// "Item has been removed"
		return new ResponseEntity<ApiResponse>(HttpStatus.OK);
	}

}
