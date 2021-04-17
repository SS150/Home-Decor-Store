package com;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.model.Category;
import com.model.Customer;
import com.model.Item;
import com.model.Order;
import com.model.Payment;
import com.model.PaymentReceip;
import com.model.Role;
import com.model.User;
import com.repository.PaymentRepository;
import com.repository.UserRepository;
import com.service.PaymentService;

@SpringBootTest
public class TestPaymentService {
	
	@Autowired
	private PaymentService paymentservice;
	
	@MockBean
	private PaymentRepository paymentrepository;
	
	
	
	@Test
	public void testPaymentDetails() throws Exception {
		Payment payment = new Payment();
		payment.setAmount(30000);
		payment.setCreatedAt(new Date());
		payment.setCustomer(new Customer());
		payment.setDescription("Payment by cash");
		payment.setPaymentId(1902);
		
		List<Payment> paymentList = new ArrayList<>();
		paymentList.add(payment);
		
		
		Mockito.when(paymentrepository.findAll()).thenReturn(paymentList);
		assertThat(paymentservice.paymentDetails(new User())).isEqualTo(paymentList);
		
	}
	
	@Test
	public void TestmakePayment() throws Exception {
		
		Role role = new Role();
		role.setDescription("For testing user ");
		role.setTitle("Testing");
		role.setRid(2);
		
		Customer customer =new Customer();
		customer.setContact("8308327198");
		customer.setEmail("arun@gmail.com");
		customer.setName("arun");
		customer.setPassword("password");
		customer.setUserName("arun");
		customer.setRole(role);
		customer.setPid(2);
		
		Category category = new Category();
		category.setcId(3);
		category.setCreatedAt(new Date());
		category.setDescriptio("Variety Furnitures for Home");
		category.setName("Furniture");
		category.setUpdatedAt(new Date());
		category.setUser(new User());
		
		Item item = new Item();
		item.setCategory(category);
		item.setCreatedAt(new Date());
		item.setDescription("Dining table wooden finish with glass top");
		item.setItemId(5);
		item.setItemName("Table");
		item.setPrice(15000);
		item.setUpdatedAt(new Date());
		
		
		Order order = new Order();
		order.setCreatedAt(new Date());
		order.setCustomer(customer);
		order.setDescription("Table for Dining");
		order.setItem(item);
		order.setOrderId(22);
		order.setOrderNumber(2);
		order.setTotalAmount(30000);
		
		
		Payment paymentNew = new Payment();
		paymentNew.setAmount(30000);
		paymentNew.setCreatedAt(new Date());
		paymentNew.setCustomer(customer);
		paymentNew.setDescription("Payment by cash");
		paymentNew.setPaymentId(1902);
		
		
		Mockito.when(paymentrepository.save(paymentNew)).thenReturn(paymentNew);
		assertThat(paymentservice.makePayment(paymentNew, customer, 0, order)).isEqualTo(paymentNew);
	}
	
	
	

}
