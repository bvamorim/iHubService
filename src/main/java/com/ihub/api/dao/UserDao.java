package com.ihub.api.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ihub.api.model.domainobject.User;

/**
 * <h1>UserDao</h1>
 * 
 * @author Bruno Amorim
 * @version 1.0
 * @since 2021-05-29
 */
@Repository
public interface UserDao extends CrudRepository<User, Long> {

	User findByUsername(String username);

}