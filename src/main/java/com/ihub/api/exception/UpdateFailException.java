package com.ihub.api.exception;

/**
 * <h1>UpdateFailException</h1>
 * 
 * @author Bruno Amorim
 * @version 1.0
 * @since 2021-05-30
 */
public class UpdateFailException extends IllegalArgumentException {

	private static final long serialVersionUID = -6619145324044866338L;

	public UpdateFailException(String msg) {
        super(msg);
    }
}