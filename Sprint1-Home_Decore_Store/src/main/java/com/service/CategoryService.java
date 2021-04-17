package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.advice.IDNotFoundException;
import com.advice.LogInNotFoundException;
import com.model.Category;
import com.model.Customer;
import com.model.User;
import com.repository.CategoryRepository;

@Service
public class CategoryService {
	
	@Autowired
	CategoryRepository categoryRepository;
	
	
	public Category addCategory(Category category,User user) throws Exception {
		
		if(user != null){
			
			category.setUser(user);
			return categoryRepository.save(category);
		}
		else {
			throw new LogInNotFoundException("You Can't Login.You Need To Login First");
		}
		
	}
	
	
	public Category updateCategory(Category category, int categoryId,User user) throws Exception {
			
			if(user != null) {
				
				Category category1 = categoryRepository.findById(categoryId).orElseThrow(()-> new IDNotFoundException("Category Are Not Present"));
				 
				 category1.setName(category.getName());
				 category1.setUpdatedAt(category.getCreatedAt());
				 category1.setUser(user);
				 return categoryRepository.save(category1);
				
			}else {
				throw new LogInNotFoundException("You Can't Login.You Need To Login First");
				}	
		}

		
		public String deleteCategory(int categoryId,User user) throws Exception {
			
			if(user != null) {
			
				Category category = categoryRepository.findById(categoryId).orElseThrow(()-> new IDNotFoundException("ID not Found "));
				categoryRepository.delete(category);
				return "Delete Succesfully";
			}
			else {
				throw new LogInNotFoundException("You Can't Login.You Need To Login First");
			}
			
		}
		
		//List  CATEGORY
		public List<Category> getCategoryList(User user) throws Exception {
			if(user != null) {
				
				return categoryRepository.findAll();
				
			}else {
				throw new LogInNotFoundException("You Can't Login.You Need To Login First");
			}
			
		}


		public Category viewCategory(int categoryId, User user) throws Exception {
			if(user != null) {
				
				 return  categoryRepository.findById(categoryId).orElseThrow(()-> new IDNotFoundException("Category Are Not Present"));
				
			}else {
				throw new LogInNotFoundException("You Can't Login.You Need To Login First");
			}	
			
		}
		
		public List<Category> getCategoryList(Customer customer) throws Exception {
			if(customer != null) {		
			return categoryRepository.findAll();
			}
			else {
				throw new LogInNotFoundException("You Can't Login First Do You Login ");
			}
		}


}
