package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.model.Category;
import com.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	
	@Query(" from User where email =?1")
	User findByEmail(String email);

	
	}
	


