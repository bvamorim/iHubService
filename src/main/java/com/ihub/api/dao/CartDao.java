package com.ihub.api.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ihub.api.model.domainobject.Cart;
import com.ihub.api.model.domainobject.User;

/**
 * <h1>CartDao</h1>
 * 
 * @author Bruno Amorim
 * @version 1.0
 * @since 2021-05-30
 */
@Repository
public interface CartDao extends CrudRepository<Cart, Long> {

	List<Cart> findAllByUserIdOrderByCreatedDateDesc(Long userId);

	List<Cart> deleteByUser(User user);

}