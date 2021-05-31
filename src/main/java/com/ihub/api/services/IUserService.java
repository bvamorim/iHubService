package com.ihub.api.services;

import com.ihub.api.model.domainobject.User;

/**
 * <h1>IUserService</h1>
 * 
 * @author Bruno Amorim
 * @version 1.0
 * @since 2021-05-29
 */
public interface IUserService {

	public User getLoggedUser();

}
