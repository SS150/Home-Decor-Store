package com;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.model.Category;
import com.model.Role;
import com.model.User;
import com.repository.CategoryRepository;
import com.service.CategoryService;



@SpringBootTest
public class CategoryServiceTest {

	@Autowired
	private CategoryService categoryService;

	@MockBean
	private CategoryRepository categoryRepository;
	
	//Get
	
	public void testGetCategoryList() throws Exception {
		Role role1 = new Role();

		role1.setRid(1);
		role1.setDescription("For Testing");
		role1.setTitle("Test");

		User user1 = new User();
		user1.setPid(1);
		user1.setContact("9988776655");
		user1.setEmail("abc@gmail.com");
		user1.setName("Arunabha");
		user1.setRole(role1);
		user1.setUserName("arunabha");
		user1.setPassword("1234");

		Category category1 = new Category();

		category1.setcId(1);
		category1.setCreatedAt(new Date());
		category1.setDescriptio("Basic");
		category1.setName("A1");
		category1.setUpdatedAt(new Date());
		category1.setUser(user1);

		Category category2 = new Category();

		category2.setcId(1);
		category2.setCreatedAt(new Date());
		category2.setDescriptio("Basic");
		category2.setName("A1");
		category2.setUpdatedAt(new Date());
		category2.setUser(user1);
		

		List<Category> categoryList = new ArrayList<>();
		categoryList.add(category1);
		categoryList.add(category2);

		Mockito.when(categoryRepository.findAll()).thenReturn(categoryList);
		
		assertThat(categoryService.getCategoryList(user1)).isEqualTo(categoryList);

	}
	
	
	
	//Add
	
	@Test 
	public void testAddCategory() throws Exception
	{
		Role role = new Role();

		role.setRid(1);
		role.setDescription("For Testing");
		role.setTitle("Test");
		
		User user = new User();
		
		user.setPid(1);
		user.setContact("9988776655");
		user.setEmail("abc@gmail.com");
		user.setName("Arunabha");
		user.setRole(role);
		user.setUserName("arunabha");
		user.setPassword("1234");

		Category category = new Category();
		
		category.setcId(1);
		category.setCreatedAt(new Date());
		category.setDescriptio("Basic");
		category.setName("A1");
		category.setUpdatedAt(new Date());
		category.setUser(user);
		
		Mockito.when(categoryRepository.save(category)).thenReturn(category);
		
		assertThat(categoryService.addCategory(category,user)).isEqualTo(category);
	}
	
	
	
	//Update
	
	@Test
	public void testUpdateCategory() throws Exception 
	{
		Role role = new Role();

		role.setRid(1);
		role.setDescription("For Testing");
		role.setTitle("Test");

		User user = new User();
		user.setPid(1);
		user.setContact("9988776655");
		user.setEmail("abc@gmail.com");
		user.setName("Arunabha");
		user.setRole(role);
		user.setUserName("arunabha");
		user.setPassword("1234");

		Category category = new Category();

		category.setcId(1);
		category.setCreatedAt(new Date());
		category.setDescriptio("Basic");
		category.setName("A1");
		category.setUpdatedAt(new Date());
		category.setUser(user);
		
		Category categoryNew = new Category();

		categoryNew.setcId(1);
		categoryNew.setCreatedAt(new Date());
		categoryNew.setDescriptio("New");
		categoryNew.setName("A2");
		categoryNew.setUpdatedAt(new Date());
		categoryNew.setUser(user);
		
		
		Optional<Category> category1 = Optional.ofNullable(category);
		
		Mockito.when(categoryRepository.findById(1)).thenReturn(category1);
		Mockito.when(categoryRepository.save(category)).thenReturn(categoryNew);
		
		assertThat(categoryService.updateCategory(category,1,user)).isEqualTo(categoryNew);
	}
	
	
	//Delete

	@Test 
	public void testDeleteCategory() throws Exception
	{
		Role role = new Role();

		role.setRid(1);
		role.setDescription("For Testing");
		role.setTitle("Test");

		User user = new User();
		user.setPid(1);
		user.setContact("9988776655");
		user.setEmail("abc@gmail.com");
		user.setName("Arunabha");
		user.setRole(role);
		user.setUserName("arunabha");
		user.setPassword("1234");

		Category category = new Category();

		category.setcId(1);
		category.setCreatedAt(new Date());
		category.setDescriptio("Basic");
		category.setName("A1");
		category.setUpdatedAt(new Date());
		category.setUser(user);

		
		Optional<Category> category1 = Optional.ofNullable(category);
		
		Mockito.when(categoryRepository.findById(1)).thenReturn(category1);
		
		assertThat(categoryService.deleteCategory(1,user)).isEqualTo("Delete Succesfully");
		
	}

	
	
	
	
	

}
