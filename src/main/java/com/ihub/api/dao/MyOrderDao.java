package com.ihub.api.dao;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ihub.api.model.domainobject.MyOrder;

/**
 * <h1>MyOrderDao</h1>
 * 
 * @author Bruno Amorim
 * @version 1.0
 * @since 2021-05-30
 */
@Repository
public interface MyOrderDao extends CrudRepository<MyOrder, Long> {

	List<MyOrder> findAllByUserIdOrderByCreatedDateDesc(Long userId);

	@Query("select a from MyOrder a where a.createdDate BETWEEN :initDate and :finalDate")
	List<MyOrder> findAllByCreatedDateBetween(@Param("initDate") LocalDateTime initDate,
			@Param("finalDate") LocalDateTime finalDate);

//    List<MyOrder> findAllByUserIdAndCreatedDateTimeBetween(
//    	      Date publicationTimeStart,
//    	      Date publicationTimeEnd);
}


