package com.service;

import java.util.List;

import javax.naming.NameNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.advice.IDNotFoundException;
import com.advice.LogInNotFoundException;
import com.model.Category;
import com.model.Customer;
import com.model.Item;
import com.model.User;
import com.repository.CategoryRepository;
import com.repository.ItemRepository;

@Service
public class ItemService {
	
	@Autowired
	ItemRepository itemRepository;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	public Item addItem(Item item, int categoryId,User user) throws Exception {
		if(user !=  null) 
		{
			Category category = categoryRepository.findById(categoryId).orElseThrow(()-> new IDNotFoundException("Category Not Present"));
			item.setCategory(category);
			return itemRepository.save(item);
		}
		else {
		throw new LogInNotFoundException("You Can't Login.You Need To Login First");
		}
	}

	public String deleteItem(int itemId, User user) throws Exception {

		if(user != null) {
			Item item = itemRepository.findById(itemId).orElseThrow(()-> new IDNotFoundException("Item ID Are Not Present"));
			itemRepository.delete(item);
			return "Delete Succesfuly  ";
		}
		else {
			throw new LogInNotFoundException("You Can't Login.You Need To Login First");
		}
		
	}

// UPDATE ITEM
	public Item updateItem(Item item, int itemId,User user) throws Exception {
		if(user != null) {

			Item item2=itemRepository.findById(itemId).orElseThrow(()-> new IDNotFoundException("Item is Not Present You Enter Wrong Item ID"));
			
			item2.setPrice(item.getPrice());
			item2.setUpdatedAt(item.getCreatedAt());
			item2.setItemName(item.getItemName());
			item2.setPrice(item.getPrice());
			item2.setDescription(item.getDescription());
			
	
			return itemRepository.save(item2);
			
		}else {
			throw new LogInNotFoundException("You Can't Login.You Need To Login First");
		}
		
	}

	//LIST OF ITEM
	public List<Item> getItem(User user) throws Exception {
		if(user != null) {
			
			return itemRepository.findAll();
			
		}else {
			throw new LogInNotFoundException("You Can't Login.You Need To Login First");
		}
	}
	
	public Item viewItem(int itemId,User user) throws Exception {
		if(user != null) {
			return  itemRepository.findById(itemId).orElseThrow(()-> new IDNotFoundException("Item Are Not Present"));
			
			}
			else {
				throw new LogInNotFoundException("You Can't Login.You Need To Login First");
			}
	}


	public List<Item> getItemList(User user) throws Exception {
		if(user != null) {
			return  itemRepository.findAll();
			
			}
			else {
				throw new LogInNotFoundException("You Can't Login.You Need To Login First");
			}
	}


	public  Item getItemByName(String itemName,User user) throws Exception {
		if(user != null) {
			  Item item = itemRepository.findByName(itemName);
			 if(item != null) {
				 return item;
			 }
			 else {
				 throw new NameNotFoundException("Name Not Found");
			 }
			
		}
		else {
				throw new LogInNotFoundException("You Can't Login.You Need To Login First");
		}
	}


	public List<Item> getItemByPrice(long price,User user) throws Exception {
		if(user != null) {
			List<Item> item = itemRepository.findByPrice(price);
			if(!item.isEmpty()) {
				 return item;
			 }
			 else {
				 throw new NameNotFoundException("Price Not Found");
			 }
					
		}
		else {
				throw new LogInNotFoundException("You Can't Login.You Need To Login First");
		}
	}
	

	public List<Item> getItem(int categoryId,Customer customer) throws Exception {
		if(customer != null) {	
		
						
			List<Item> itemList = itemRepository.findBycategoryId(categoryId);
			if(!itemList.isEmpty()) {
				return itemList;
			}
			else {
				throw new IDNotFoundException("Category ID is Empty");
			}
		}
		else {
			throw new LogInNotFoundException("You Can't Login First Do You Login ");
		}
	}
}
