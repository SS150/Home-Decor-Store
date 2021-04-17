package com;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.model.Customer;
import com.model.Login;
import com.model.Role;
import com.model.User;
import com.repository.CustomerRepository;
import com.repository.RoleRepository;
import com.service.CustomerService;
@SpringBootTest
class CustomerServiceTest {
	
	@Autowired
	CustomerService customerService;
	@MockBean
	CustomerRepository customerRepository;
	@MockBean
	RoleRepository roleRepository;

	@Test
	public void getLoginTest() throws Exception {
		
		Login login = new Login();
		login.setPassword("test");
		login.setUserName("Test");
		
		Role role = new Role();
		role.setDescription("For testing user ");
		role.setTitle("Testing");
		role.setRid(1);
			
		Customer customer = new Customer();
		customer.setContact("8308327198");
		customer.setEmail("tush@gmail.com");
		customer.setName("TestRaju");
		customer.setPassword("test");
		customer.setUserName("Test");
		customer.setAddress("mumbai");
		customer.setRole(role);
		customer.setPid(2);
		
		Customer customerNew = new Customer();
		customerNew.setContact("8308389555");
		customerNew.setEmail("Ramu@gmail.com");
		customerNew.setName("Ramu");
		customerNew.setPassword("ramu");
		customerNew.setUserName("ramu");
		customerNew.setAddress("pune");
		customerNew.setRole(role);
		customerNew.setPid(3);
		
		Customer customerold = null;
		List<Customer> list = new ArrayList<Customer>();
		list.add(customer);
		list.add(customerNew);
		

		Mockito.when(customerRepository.findAll()).thenReturn(list);
		assertThat(customerService.getCustomerLogin(login,customerold)).isEqualTo(customer);
		
		
	}
	
	@Test
	public void addCustomerTest() throws Exception {
		
		Role role = new Role();
		role.setDescription("For testing user ");
		role.setTitle("Testing");
		role.setRid(1);
		
		
		
		Customer customer = new Customer();
		customer.setContact("8308327198");
		customer.setEmail("tush@gmail.com");
		customer.setName("TestRaju");
		customer.setPassword("test");
		customer.setUserName("Test");
		customer.setAddress("mumbai");
		customer.setRole(role);
		customer.setPid(2);
		
		Optional<Role> role1=Optional.ofNullable(role);
		Mockito.when(roleRepository.findById(1)).thenReturn(role1);
		Mockito.when(customerRepository.save(customer)).thenReturn(customer);
		
		    
		assertThat(customerService.addCustomer(customer,1)).isEqualTo(customer);
		   
		
		
	}
	
	@Test
	public void getCustomerDetails() throws Exception {
		
		Role role = new Role();
		role.setDescription("For testing user ");
		role.setTitle("Testing");
		role.setRid(1);
		
		
		
		Customer customer = new Customer();
		customer.setContact("8308327198");
		customer.setEmail("tush@gmail.com");
		customer.setName("TestRaju");
		customer.setPassword("test");
		customer.setUserName("Test");
		customer.setAddress("mumbai");
		customer.setRole(role);
		customer.setPid(2);
		

		Optional<Customer> customer1=Optional.ofNullable(customer);
		Mockito.when(customerRepository.findById(2)).thenReturn(customer1);
		assertThat(customerService.getCustomerdetails(customer)).isEqualTo(customer);
	}

	@Test
	public void updateCustomer() throws Exception {
		

		Role role = new Role();
		role.setDescription("For testing user ");
		role.setTitle("Testing");
		role.setRid(1);
		
		
		
		Customer customer = new Customer();
		customer.setContact("8308327198");
		customer.setEmail("tush@gmail.com");
		customer.setName("TestRaju");
		customer.setPassword("test");
		customer.setUserName("Test");
		customer.setAddress("mumbai");
		customer.setRole(role);
		customer.setPid(2);
		
		Customer customerNew = new Customer();
		customerNew.setContact("8308857198");
		customerNew.setEmail("pinku@gmail.com");
		customerNew.setName("TestPinku");
		customerNew.setPassword("pinku");
		customerNew.setUserName("Test");
		customerNew.setAddress("Kolkata");
		customerNew.setRole(role);
		customerNew.setPid(2);

		Mockito.when(customerRepository.save(customer)).thenReturn(customerNew);
		assertThat(customerService.updateCustomer(customerNew,customer)).isEqualTo(customerNew);
	}

}
