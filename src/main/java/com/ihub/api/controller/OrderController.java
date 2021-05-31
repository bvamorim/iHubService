package com.ihub.api.controller;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ihub.api.exception.OrderNotFoundException;
import com.ihub.api.exception.ProductNotExistException;
import com.ihub.api.model.datatransferobject.AddToCartDto;
import com.ihub.api.model.domainobject.MyOrder;
import com.ihub.api.services.IOrderService;
import com.ihub.api.services.IUserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

/**
 * <h1>OrderController</h1>
 * 
 * @author Bruno Amorim
 * @version 1.0
 * @since 2021-05-29
 */
@RestController
@RequestMapping("/order")
@Api(tags = { "Order API" })
public class OrderController {

	@Autowired
	private IOrderService iOrderService;

	@Autowired
	private IUserService iUserService;
	
	@ModelAttribute
	LocalDateTime initLocalDate() {
	    return LocalDateTime.now();
	}

	@ApiOperation(value = "Place an order with the items of the logged-in user's Cart and cleanup the Cart")
	@PostMapping("/placeOrder")
	public ResponseEntity<ApiResponse> placeOrder() throws ProductNotExistException, OrderNotFoundException {

		iOrderService.placeOrder(iUserService.getLoggedUser());

		// "Order has been placed"
		return new ResponseEntity<ApiResponse>(HttpStatus.CREATED);
	}

	@ApiOperation(value = "Returns all orders from the logged in user")
	@GetMapping("/list")
	public ResponseEntity<List<MyOrder>> getAllOrders() {

		List<MyOrder> orderDtoList = iOrderService.listOrders(iUserService.getLoggedUser());
		return new ResponseEntity<List<MyOrder>>(orderDtoList, HttpStatus.OK);
	}

	@ApiOperation(value = "Returns all orders from the logged in user created between two dates")
	@PostMapping("/listBetween")
	public ResponseEntity<List<MyOrder>> getAllOrdersBetween(
			@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") @ModelAttribute LocalDateTime initDate,
			@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") @ModelAttribute LocalDateTime finalDate) {

		List<MyOrder> orderDtoList = iOrderService.listOrdersBetween(initDate, finalDate);
		return new ResponseEntity<List<MyOrder>>(orderDtoList, HttpStatus.OK);
	}

}
