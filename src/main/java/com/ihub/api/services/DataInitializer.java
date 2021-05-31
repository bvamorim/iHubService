package com.ihub.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.ihub.api.dao.UserDao;
import com.ihub.api.model.domainobject.User;

/**
 * <h1>DataInitializer</h1>
 * 
 * @author Bruno Amorim
 * @version 1.0
 * @since 2021-05-29
 */
@Component
public class DataInitializer {

	@Autowired
	private UserDao userDao;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	public void createAdmin() {
		User newUser = new User();
		newUser.setUsername("ihub");
		newUser.setPassword((bcryptEncoder.encode("password")));
		userDao.save(newUser);
	}
}