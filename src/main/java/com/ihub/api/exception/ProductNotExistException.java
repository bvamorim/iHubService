package com.ihub.api.exception;

/**
 * <h1>ProductNotExistException</h1>
 * 
 * @author Bruno Amorim
 * @version 1.0
 * @since 2021-05-29
 */
public class ProductNotExistException extends IllegalArgumentException  {

	private static final long serialVersionUID = -8612834010341712777L;

	public ProductNotExistException(String msg) {
        super(msg);
    }

}
