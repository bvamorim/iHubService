package com.ihub.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ihub.api.dao.UserDao;
import com.ihub.api.model.domainobject.User;

/**
 * <h1>UserService</h1>
 * 
 * @author Bruno Amorim
 * @version 1.0
 * @since 2021-05-30
 */
@Service
@Transactional
public class UserService implements IUserService {

	@Autowired
	private UserDao userDao;

	public UserService() {
	}

	public UserService(UserDao userDao) {
		this.userDao = userDao;
	}

	public User getLoggedUser() {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = userDetails.getUsername();

		User user = userDao.findByUsername(username);
		return user;
	}

}
