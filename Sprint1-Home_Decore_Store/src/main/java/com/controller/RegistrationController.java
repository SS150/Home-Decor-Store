package com.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.model.Customer;
import com.model.Person;
import com.model.Role;
import com.model.User;
import com.repository.RoleRepository;
import com.service.CustomerService;
import com.service.RoleService;
import com.service.UserService;

@RestController("/registration")
public class RegistrationController {
	
	
	@Autowired
	UserService userService;
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	RoleService roleService;
	
		
	@PostMapping("/registerForUser/{roleId}")
	public ResponseEntity<User> addUser( @RequestBody User user,@PathVariable int roleId) throws Exception {
		return new ResponseEntity<User>(userService.addUser(user,roleId),HttpStatus.OK);
		
	}
	@PostMapping("/registerForCustomer/{roleId}")
	public ResponseEntity<Customer> addCustomer(@Valid @RequestBody Customer customer ,@PathVariable int roleId)throws Exception {
		return new ResponseEntity<Customer>(customerService.addCustomer(customer,roleId),HttpStatus.OK);
		
	}
	
	
	
	@GetMapping("/getRole")
	public ResponseEntity<List<Role>> getRole() throws Exception {
		return new ResponseEntity<List<Role>>(roleService.getRole(),HttpStatus.OK); 
	}

	@GetMapping("/forgetPasswordForUser/{email}/{name}")
	public ResponseEntity<String> getpassword(@PathVariable String email,@PathVariable String name) throws Exception{
		return new ResponseEntity<String>(userService.getPass(email,name),HttpStatus.OK);
	}
	
	@GetMapping("/forgetPasswordForCustomer/{email}/{name}")
	public ResponseEntity<String> getpasswordCustomer(@PathVariable String email,@PathVariable String name) throws Exception{
		return new ResponseEntity<String>(customerService.getPass(email,name),HttpStatus.OK);
	}
}
