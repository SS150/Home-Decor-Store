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
import com.model.Item;
import com.model.Role;
import com.model.User;
import com.repository.CategoryRepository;
import com.repository.ItemRepository;
import com.service.ItemService;

@SpringBootTest
public class ItemServiceTest {

	@Autowired
	private ItemService itemService;



	@MockBean
	private ItemRepository itemRepository;

	@MockBean
	private CategoryRepository categoryRepository;

	
	
	//Get

	@Test
	public void testGetItem() throws Exception {
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

		Item item1 = new Item();

		item1.setItemId(1);
		item1.setCategory(category1);
		item1.setDescription("Basic");
		item1.setItemName("A1_a1");
		item1.setPrice(1200l);
		item1.setCreatedAt(new Date());
		item1.setUpdatedAt(new Date());

		Category category2 = new Category();

		category2.setcId(1);
		category2.setCreatedAt(new Date());
		category2.setDescriptio("Basic");
		category2.setName("A1");
		category2.setUpdatedAt(new Date());
		category2.setUser(user1);

		Item item2 = new Item();

		item2.setItemId(1);
		item2.setCategory(category1);
		item2.setDescription("Basic");
		item2.setItemName("A1_a1");
		item2.setPrice(1200l);
		item2.setCreatedAt(new Date());
		item2.setUpdatedAt(new Date());

		List<Item> itemList = new ArrayList<>();
		itemList.add(item1);
		itemList.add(item2);

		Mockito.when(itemRepository.findAll()).thenReturn(itemList);
		
		assertThat(itemService.getItemList(user1)).isEqualTo(itemList);
	}

	@Test
	public void testGetItemByName() throws Exception {
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

		Item item = new Item();

		item.setItemId(1);
		item.setCategory(category);
		item.setDescription("Testing");
		item.setItemName("Test");
		item.setPrice(1200l);
		item.setCreatedAt(new Date());
		item.setUpdatedAt(new Date());

		
		Mockito.when(itemRepository.findByName("Test")).thenReturn(item);
		
		assertThat(itemService.getItemByName("Test",user)).isEqualTo(item);

	}

	
	
	
	//Add
	
	@Test 
	public void testAddItem() throws Exception
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

		Item item = new Item();

		item.setItemId(1);
		item.setCategory(category);
		item.setDescription("Testing");
		item.setItemName("Test");
		item.setPrice(1200l);
		item.setCreatedAt(new Date());
		item.setUpdatedAt(new Date());
		
		Optional<Category> category1 = Optional.ofNullable(category);
		
		Mockito.when(categoryRepository.findById(1)).thenReturn(category1);
		Mockito.when(itemRepository.save(item)).thenReturn(item);
		
		assertThat(itemService.addItem(item,1,user)).isEqualTo(item);
			
	}
		
	
	
	//Update
	
	@Test
	public void testUpdateItem() throws Exception {
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

		Item item = new Item();

		item.setItemId(1);
		item.setCategory(category);
		item.setDescription("Testing");
		item.setItemName("Test");
		item.setPrice(1200l);
		item.setCreatedAt(new Date());
		item.setUpdatedAt(new Date());

		Item itemNew = new Item();

		itemNew.setItemId(1);
		itemNew.setCategory(category);
		itemNew.setDescription("Testing");
		itemNew.setItemName("Test");
		itemNew.setPrice(1200l);
		itemNew.setCreatedAt(new Date());
		itemNew.setUpdatedAt(new Date());
		
		Optional<Item> item1=Optional.ofNullable(item);
		
		Mockito.when(itemRepository.findById(1)).thenReturn(item1);
		Mockito.when(itemRepository.save(item)).thenReturn(itemNew);

		assertThat(itemService.updateItem(item,1,user)).isEqualTo(itemNew);

	}
	
	
	
	//Delete
	
	@Test 
	public void testDeleteItem() throws Exception
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

		Item item = new Item();

		item.setItemId(1);
		item.setCategory(category);
		item.setDescription("Testing");
		item.setItemName("Test");
		item.setPrice(1200l);
		item.setCreatedAt(new Date());
		item.setUpdatedAt(new Date());
		
		String message="Deleted Successfully";
		Optional<Item> item1 = Optional.ofNullable(item);
		
		Mockito.when(itemRepository.findById(1)).thenReturn(item1);
		Mockito.when(itemRepository.existsById(item.getItemId())).thenReturn(false);
		
		
		assertThat(itemService.deleteItem(1,user)).isEqualTo("Delete Succesfuly  ");
		
	}

}
