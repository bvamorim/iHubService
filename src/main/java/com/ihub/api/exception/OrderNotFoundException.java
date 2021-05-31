package com.ihub.api.exception;

/**
 * <h1>OrderNotFoundException</h1>
 * 
 * @author Bruno Amorim
 * @version 1.0
 * @since 2021-05-29
 */
public class OrderNotFoundException extends IllegalArgumentException  {

	private static final long serialVersionUID = -4734298162675619840L;
	
    public OrderNotFoundException(String msg) {
        super(msg);
    }

}
