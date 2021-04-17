package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.model.ShoppinCard;

public interface ShoppingCardRepository extends JpaRepository<ShoppinCard, Integer>{

	@Query(" from ShoppinCard where CUSTOMER_ID =?1")
	List<ShoppinCard> findAllByCId(int pid);

}
