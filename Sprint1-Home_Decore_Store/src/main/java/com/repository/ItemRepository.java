package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.model.Item;

public interface ItemRepository extends JpaRepository<Item, Integer> {

	@Query(" from Item where CATEGORY_ID=?1")
	List<Item> findBycategoryId(int categoryId);

	@Query("from Item where itemName=?1")
	Item findByName(String itemName);

	@Query("from Item where PRICE=?1")
	List<Item> findByPrice(long price);

}
