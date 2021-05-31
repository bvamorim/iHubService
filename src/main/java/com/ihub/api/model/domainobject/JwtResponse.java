package com.ihub.api.model.domainobject;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * <h1>JwtResponse</h1>
 * 
 * @author Bruno Amorim
 * @version 1.0
 * @since 2021-05-29
 */
@Getter
@Setter
public class JwtResponse implements Serializable {

	private static final long serialVersionUID = -8091879091924046844L;
	private String jwttoken = "";

	public JwtResponse() {
	}

	public JwtResponse(String token) {
		this.jwttoken = token;
	}

	public String getJwttoken() {
		return jwttoken;
	}

	public void setJwttoken(String jwttoken) {
		this.jwttoken = jwttoken;
	}

	public String getToken() {
		return this.jwttoken;
	}
	
}