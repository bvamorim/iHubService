package com.ihub.api.exception;

/**
 * <h1>CartItemNotExistException</h1>
 * 
 * @author Bruno Amorim
 * @version 1.0
 * @since 2021-05-30
 */
public class CartItemNotExistException extends IllegalArgumentException {
	private static final long serialVersionUID = 7942968945424729332L;

	public CartItemNotExistException(String msg) {
		super(msg);
	}
}