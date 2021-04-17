package com.service;

import java.util.List;

import javax.naming.NameNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.advice.ContactInValidException;
import com.advice.IDNotFoundException;
import com.advice.InvalidCredentialException;
import com.advice.LogInNotFoundException;
import com.model.Category;
import com.model.ContactValid;
import com.model.Customer;
import com.model.Item;
import com.model.Login;
import com.model.Order;
import com.model.Payment;
import com.model.PaymentReceip;
import com.model.Role;
import com.model.ShoppinCard;
import com.model.User;
import com.repository.CategoryRepository;
import com.repository.CustomerRepository;
import com.repository.ItemRepository;
import com.repository.OrderRepository;
import com.repository.PaymentRepository;
import com.repository.RoleRepository;
import com.repository.ShoppingCardRepository;

@Service
public class CustomerService {

	
	
	
	
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	
	ContactValid contactIsValid = new ContactValid();

	
	
	
	public Customer getCustomerLogin(Login login,Customer customer1) throws Exception {
		
		boolean present = false;
		String userName=login.getUserName();
		
		String password=login.getPassword();
	
		List<Customer> list = customerRepository.findAll();
		
		for(Customer customer:list) {
			
			if(customer.getUserName().equals(userName) && customer.getPassword().equals(password)) {
				customer1 =customer;
				present=true;
			}
			
		}
		if(present == true) {
			return customer1;
		}
		else {
			throw new InvalidCredentialException("Your Entering Wrong UserId and Password Please Enter Valid Credential");
		}
		
	}
	//At time register
	public Customer addCustomer(Customer customer,int roleId) throws Exception{
		
		
		Role role = roleRepository.findById(roleId).orElseThrow(()-> new IDNotFoundException("Role ID Not Found"));
		customer.setRole(role);
		
		if(contactIsValid.isValidContact(customer.getContact())){
			return customerRepository.save(customer);
		}
		else {
			throw new ContactInValidException("Enter Only Number In Contact Number");
		}
	
}
	
	
	
	public Customer getCustomerdetails(Customer customer) throws Exception {
		if(customer!=null) 
		{
			int customerId=customer.getPid();
			return customerRepository.findById(customerId).orElseThrow(()-> new IDNotFoundException("Customer Are Not Present "));
		}
		else {
			throw new LogInNotFoundException("You Can't Login First Do You Login ");
		}
		
	}
	
	public Customer updateCustomer(Customer customerNew,Customer customerOld) throws Exception {
		if(customerOld != null) {
			
			customerOld.setContact(customerNew.getContact());
			customerOld.setAddress(customerNew.getAddress());
			customerOld.setEmail(customerNew.getEmail());
			customerOld.setName(customerNew.getName());
			
			
			return customerRepository.save(customerOld);
			
		}
		else {
			throw new LogInNotFoundException("You Can't Login. First You Do Login ");
		}
	}
	
	
	public List<Customer> getCustomer(User user) throws Exception {
		if(user != null)
		{
			return customerRepository.findAll();
		}
		else {
			throw new LogInNotFoundException("You Can't Login.You Need To Login First");
		}
	}


	public Customer addCustomer(Customer customer,User user,int roleId) throws Exception {
		if( user != null) {

			Role role = roleRepository.findById(roleId).orElseThrow(()-> new IDNotFoundException("Role ID Not Found"));
			customer.setRole(role);
			return customerRepository.save(customer);
		}
		else {
			throw new LogInNotFoundException("You Can't Login.You Need To Login First");
		}
	}


	public String deleteCustomer(int customerId,User user) throws Exception {
		
		if(user != null) {
		Customer customer= customerRepository.findById(customerId).orElseThrow(()-> new IDNotFoundException("Customer Are Not Present"));
		customerRepository.delete(customer);
		return "Delet Succesfully ";
		}
		else {
			throw new LogInNotFoundException("You Can't Login.You Need To Login First");
		}
		
		
	}


	public Customer viewCustomer(int customerId,User user) throws Exception {
		if(user != null) {
			return  customerRepository.findById(customerId).orElseThrow(()-> new IDNotFoundException("Customer Are Not Present"));
			
			}
			else {
				throw new LogInNotFoundException("You Can't Login.You Need To Login First");
			}
	}
	
	public String getPass(String email, String name) throws Exception {
		Customer customer = customerRepository.findByEmail(email);
		
		if(customer != null) {
			if(customer.getName().equals(name)) {
				return customer.getPassword();
				}else {
					throw new NameNotFoundException("Name Not Found ");
				}
			
		}else {
			throw new NameNotFoundException("Email is not present Please Enter correct Email");
		}
	}





	

}
