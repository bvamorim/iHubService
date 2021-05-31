package com.ihub.api.services;

import com.ihub.api.exception.CartItemNotExistException;
import com.ihub.api.model.datatransferobject.AddToCartDto;
import com.ihub.api.model.datatransferobject.CartDto;
import com.ihub.api.model.domainobject.Cart;
import com.ihub.api.model.domainobject.Product;
import com.ihub.api.model.domainobject.User;

/**
 * <h1>ICartService</h1>
 * 
 * @author Bruno Amorim
 * @version 1.0
 * @since 2021-05-29
 */
public interface ICartService {

	public Cart addToCart(AddToCartDto addToCartDto, Product product, User user);

	public CartDto listCartItems(Long userId);

	public void updateCartItem(AddToCartDto cartDto, User user, Product product);

	public void deleteCartItem(Long id) throws CartItemNotExistException;

	public void deleteCartItems(Long userId);

	public void deleteUserCartItems(User user);

}
