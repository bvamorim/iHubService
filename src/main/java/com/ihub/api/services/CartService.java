package com.ihub.api.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ihub.api.dao.CartDao;
import com.ihub.api.exception.CartItemNotExistException;
import com.ihub.api.model.datatransferobject.AddToCartDto;
import com.ihub.api.model.datatransferobject.CartDto;
import com.ihub.api.model.datatransferobject.CartItemDto;
import com.ihub.api.model.domainobject.Cart;
import com.ihub.api.model.domainobject.Product;
import com.ihub.api.model.domainobject.User;

/**
 * <h1>CartService</h1>
 * 
 * @author Bruno Amorim
 * @version 1.0
 * @since 2021-05-30
 */
@Service
@Transactional
public class CartService implements ICartService {

	@Autowired
	private CartDao cartDao;

	public CartService() {
	}

	public CartService(CartDao cartDao) {
		this.cartDao = cartDao;
	}

	public Cart addToCart(AddToCartDto addToCartDto, Product product, User user) {
		Cart cart = new Cart(product, addToCartDto.getQuantity(), user);
		return cartDao.save(cart);
	}

	public CartDto listCartItems(Long userId) {
		List<Cart> cartList = cartDao.findAllByUserIdOrderByCreatedDateDesc(userId);
		List<CartItemDto> cartItems = new ArrayList<>();
		for (Cart cart : cartList) {
			CartItemDto cartItemDto = getDtoFromCart(cart);
			cartItems.add(cartItemDto);
		}
		double totalCost = 0;
		for (CartItemDto cartItemDto : cartItems) {
			totalCost += (cartItemDto.getProduct().getPrice() * cartItemDto.getQuantity());
		}
		CartDto cartDto = new CartDto(cartItems, totalCost);
		return cartDto;
	}

	public static CartItemDto getDtoFromCart(Cart cart) {
		CartItemDto cartItemDto = new CartItemDto(cart);
		return cartItemDto;
	}

	public void updateCartItem(AddToCartDto cartDto, User user, Product product) {
		if (!cartDao.existsById(cartDto.getId()))
			throw new CartItemNotExistException("Cart id is invalid : " + cartDto.getId());
		Optional<Cart> cartOpt = cartDao.findById(cartDto.getId());
		if (cartOpt.isPresent()) {
			Cart cart = cartOpt.get();
			cart.setQuantity(cartDto.getQuantity());
			cart.setCreatedDate(LocalDateTime.now());
		}

	}

	public void deleteCartItem(Long id) throws CartItemNotExistException {
		if (!cartDao.existsById(id))
			throw new CartItemNotExistException("Cart id is invalid : " + id);
		cartDao.deleteById(id);
	}

	public void deleteCartItems(Long userId) {
		cartDao.deleteAll();
	}

	public void deleteUserCartItems(User user) {
		cartDao.deleteByUser(user);
	}

}
