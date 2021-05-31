package com.ihub.api.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ihub.api.dao.MyOrderDao;
import com.ihub.api.exception.OrderNotFoundException;
import com.ihub.api.model.datatransferobject.CartDto;
import com.ihub.api.model.datatransferobject.CartItemDto;
import com.ihub.api.model.datatransferobject.PlaceOrderDto;
import com.ihub.api.model.domainobject.MyOrder;
import com.ihub.api.model.domainobject.OrderItem;
import com.ihub.api.model.domainobject.User;

/**
 * <h1>OrderService</h1>
 * 
 * @author Bruno Amorim
 * @version 1.0
 * @since 2021-05-29
 */
@Service
@Transactional
public class OrderService implements IOrderService {

	@Autowired
	private MyOrderDao myOrderDao;

	@Autowired
	private ICartService iCartService;

	@SuppressWarnings("unused")
	@Autowired
	private IOrderItemsService iOrderItemsService;

	public MyOrder saveOrder(PlaceOrderDto orderDto, User user) throws OrderNotFoundException {
		MyOrder myOrder = new MyOrder(orderDto, user.getId());
		myOrder.setUser(user);
		return myOrderDao.save(myOrder);
	}

	public MyOrder getOrderFromDto(PlaceOrderDto orderDto, User user) throws OrderNotFoundException {
		MyOrder myOrder = new MyOrder(orderDto, user.getId());
		if (myOrder.getId() == null) {
			throw new OrderNotFoundException("Order not found");
		}
		return myOrder;

	}

	public List<MyOrder> listOrders(User user) {
		List<MyOrder> orderList = myOrderDao.findAllByUserIdOrderByCreatedDateDesc(user.getId());
		return orderList;
	}

	public MyOrder getMyOrder(Long orderId) throws OrderNotFoundException {
		Optional<MyOrder> order = myOrderDao.findById(orderId);
		if (order.isPresent()) {
			return order.get();
		}
		throw new OrderNotFoundException("Order not found");
	}

	public void placeOrder(User user) throws OrderNotFoundException {
		CartDto cartDto = iCartService.listCartItems(user.getId());

		PlaceOrderDto placeOrderDto = new PlaceOrderDto();
		placeOrderDto.setUserId(user.getId());
		placeOrderDto.setTotalPrice(cartDto.getTotalCost());

		MyOrder newOrder = saveOrder(placeOrderDto, user);
		Long newOrderId = newOrder.getId();
		List<CartItemDto> cartItemDtoList = cartDto.getCartItems();
		List<OrderItem> listOrdemItem = new ArrayList<>();

		for (CartItemDto cartItemDto : cartItemDtoList) {
			OrderItem orderItem = cartItemDto.toOrdemItem(newOrderId);
			listOrdemItem.add(orderItem);
		}

		iCartService.deleteUserCartItems(user);
		newOrder.setOrderItems(listOrdemItem);

		myOrderDao.save(newOrder);
	}

	public List<MyOrder> listOrdersBetween(LocalDateTime initDate, LocalDateTime finalDate) {
		List<MyOrder> orderList = myOrderDao.findAllByCreatedDateBetween(initDate, finalDate);
		return orderList;
	}

}
