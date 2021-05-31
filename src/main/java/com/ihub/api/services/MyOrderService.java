package com.ihub.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ihub.api.dao.MyOrderDao;
import com.ihub.api.model.datatransferobject.CartDto;
import com.ihub.api.model.datatransferobject.CartItemDto;
import com.ihub.api.model.datatransferobject.PlaceOrderDto;
import com.ihub.api.model.domainobject.MyOrder;
import com.ihub.api.model.domainobject.OrderItem;

/**
 * <h1>MyOrderService</h1>
 * 
 * @author Bruno Amorim
 * @version 1.0
 * @since 2021-05-29
 */
@Service
public class MyOrderService implements IMyOrderService {

	@Autowired
	private MyOrderDao myOrderDao;

	@Autowired
	private ICartService iCartService;

	@Autowired
	private IOrderItemsService iOrderItemsService;

	public void placeOrder(Long userId) {
		CartDto cartDto = iCartService.listCartItems(userId);

		PlaceOrderDto placeOrderDto = new PlaceOrderDto();
		placeOrderDto.setUserId(userId);
		placeOrderDto.setTotalPrice(cartDto.getTotalCost());

		Long orderId = saveOrder(placeOrderDto, userId);
		List<CartItemDto> cartItemDtoList = cartDto.getCartItems();

		for (CartItemDto cartItemDto : cartItemDtoList) {
			OrderItem orderItem = new OrderItem(orderId, cartItemDto.getProduct().getId(), cartItemDto.getQuantity(),
					cartItemDto.getProduct().getPrice());
			iOrderItemsService.addOrderedProducts(orderItem);
		}
		iCartService.deleteCartItems(userId);
	}

	public Long saveOrder(PlaceOrderDto placeOrderDto, Long userId) {
		MyOrder myOrder = getOrderFromDto(placeOrderDto, userId);
		return myOrderDao.save(myOrder).getId();
	}

	public MyOrder getOrderFromDto(PlaceOrderDto placeOrderDto, Long userId) {
		MyOrder myOrder = new MyOrder(placeOrderDto, userId);
		return myOrder;
	}

	public List<MyOrder> listOrders(Long userId) {
		List<MyOrder> orderList = myOrderDao.findAllByUserIdOrderByCreatedDateDesc(userId);
		return orderList;
	}

}
