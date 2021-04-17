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
import com.model.Customer;
import com.model.Item;
import com.model.Order;
import com.model.Role;
import com.model.User;
import com.repository.ItemRepository;
import com.repository.OrderRepository;
import com.service.OrderService;


@SpringBootTest
public class OrderServiceTest {
	
	@Autowired
	private OrderService orderService;

	@MockBean
	private OrderRepository orderRepository;
	
	
	@MockBean
	private ItemRepository itemRepository;
	
	
	//Get
	
	public void testGetOrderList() throws Exception {
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
		
		Customer customer1 = new Customer();
		
		customer1.setPid(1);
		customer1.setContact("9988776655");
		customer1.setEmail("abc@gmail.com");
		customer1.setName("Arunabha");
		customer1.setRole(role);
		customer1.setUserName("arunabha");
		customer1.setPassword("1234");
		customer1.setAddress("Kolkata");
		
		Customer customer2 = new Customer();
		
		customer2.setPid(2);
		customer2.setContact("9922776655");
		customer2.setEmail("def@gmail.com");
		customer2.setName("Avinabha");
		customer2.setRole(role);
		customer2.setUserName("avinabha");
		customer2.setPassword("12345");
		customer2.setAddress("Delhi");

		Category category1 = new Category();

		category1.setcId(1);
		category1.setCreatedAt(new Date());
		category1.setDescriptio("Basic1");
		category1.setName("A1");
		category1.setUpdatedAt(new Date());
		category1.setUser(user);
		
		Item item1 = new Item();

		item1.setItemId(1);
		item1.setCategory(category1);
		item1.setDescription("Basic1");
		item1.setItemName("A1_a1");
		item1.setPrice(1200l);
		item1.setCreatedAt(new Date());
		item1.setUpdatedAt(new Date());

		Category category2 = new Category();

		category2.setcId(2);
		category2.setCreatedAt(new Date());
		category2.setDescriptio("Basic2");
		category2.setName("A2");
		category2.setUpdatedAt(new Date());
		category2.setUser(user);
		
		Item item2 = new Item();

		item2.setItemId(2);
		item2.setCategory(category2);
		item2.setDescription("Basic2");
		item2.setItemName("A1_a2");
		item2.setPrice(2200l);
		item2.setCreatedAt(new Date());
		item2.setUpdatedAt(new Date());
		
		Order order1=new Order();
		
		order1.setCreatedAt(new Date());
		order1.setCustomer(customer1);
		order1.setDescription("Testing");
		order1.setItem(item1);
		order1.setOrderId(1);
		order1.setOrderNumber(1);
		order1.setTotalAmount(10000l);
		
		Order order2=new Order();
		
		order2.setCreatedAt(new Date());
		order2.setCustomer(customer2);
		order2.setDescription("Testing");
		order2.setItem(item2);
		order2.setOrderId(2);
		order2.setOrderNumber(2);
		order2.setTotalAmount(20000l);
		
	
		List<Order> orderList = new ArrayList<>();
		orderList.add(order1);
		orderList.add(order2);

		Mockito.when(orderRepository.findAll()).thenReturn(orderList);
		
		assertThat(orderService.getOrder(user)).isEqualTo(orderList);

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
		
		Customer customer = new Customer();
		
		customer.setPid(1);
		customer.setContact("9922776655");
		customer.setEmail("def@gmail.com");
		customer.setName("Avinabha");
		customer.setRole(role);
		customer.setUserName("avinabha");
		customer.setPassword("12345");
		customer.setAddress("Kolkata");

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
		item.setDescription("Basic");
		item.setItemName("A1_a1");
		item.setPrice(1200l);
		item.setCreatedAt(new Date());
		item.setUpdatedAt(new Date());
		
		Item itemNew = new Item();

		itemNew.setItemId(2);
		itemNew.setCategory(category);
		itemNew.setDescription("New");
		itemNew.setItemName("A1_a1");
		itemNew.setPrice(2200l);
		itemNew.setCreatedAt(new Date());
		itemNew.setUpdatedAt(new Date());
		
		
		Order order=new Order();
		
		order.setCreatedAt(new Date());
		order.setCustomer(customer);
		order.setDescription("Testing");
		order.setItem(item);
		order.setOrderId(1);
		order.setOrderNumber(1);
		order.setTotalAmount(10000l);
		
		Order orderNew=new Order();
		
		orderNew.setCreatedAt(new Date());
		orderNew.setCustomer(customer);
		orderNew.setDescription("Testing");
		orderNew.setItem(itemNew);
		orderNew.setOrderId(1);
		orderNew.setOrderNumber(2);
		orderNew.setTotalAmount(20000l);
		
		
		Optional<Item> item1 = Optional.ofNullable(item);
		
		Mockito.when(itemRepository.findById(1)).thenReturn(item1);
		Mockito.when(orderRepository.save(order)).thenReturn(orderNew);
		
		assertThat(orderService.placeOrder(order,1,customer)).isEqualTo(orderNew);
	}
}

