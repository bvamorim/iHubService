package com.ihub.api.exception;

/**
* <h1>InvalidParameterException</h1>
* This Class is used to create a exception
* when a invalid parameter is received. 
* 
* @author  Bruno Amorim
* @version 1.0
* @since   2021-05-16
*/
public class InvalidParameterException extends java.security.InvalidParameterException {

	private static final long serialVersionUID = -6761597714008346691L;
	private String parameterName;
    private Object parameterValue;

    public InvalidParameterException(String parameterName, Object parameterValue) {
        this.parameterName = parameterName;
        this.parameterValue = parameterValue;

    }
    private String buildMessage(){
        return String.format("The parameter [%s] is not valid with actual value of [%s]",
                this.parameterName, this.parameterValue);
    }

    @Override
    public String getMessage() {
        return buildMessage();
    }
}
