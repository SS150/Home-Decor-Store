package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{

	@Query("from Customer where email = ?1")
	Customer findByEmail(String email);

}
