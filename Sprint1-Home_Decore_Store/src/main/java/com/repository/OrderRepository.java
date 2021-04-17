package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.model.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {

	@Query(" from Order where CUSTOMER_ID =?1")
	List<Order> findByCustomerId(int customerId);

}
