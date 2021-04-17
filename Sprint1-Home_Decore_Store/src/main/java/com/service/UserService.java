package com.service;

import java.util.List;

import javax.naming.NameNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.model.Role;
import com.model.User;
import com.repository.CategoryRepository;
import com.repository.CustomerRepository;
import com.repository.ItemRepository;
import com.repository.OrderRepository;
import com.repository.PaymentRepository;
import com.repository.RoleRepository;
import com.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;
	
	
	ContactValid contactIsValid = new ContactValid();

	

	
	public User getLogin(Login login,User userOld) throws Exception {
		if(userOld == null) {
		boolean present = false;
		String userName=login.getUserName();
		String password=login.getPassword();
		List<User> list = userRepository.findAll();
		
		for(User user:list) {
			
			if(user.getUserName().equals(userName) && user.getPassword().equals(password)) {
				userOld=user;
				present=true;

			}
		}
			if(present == true) {
			return userOld;
			}
			else {
				throw new InvalidCredentialException("Your Entering Wrong UserId and Password Please Enter Valid Credential");
			}
		}
		else {
			throw new LogInNotFoundException("Already User Login first Logout");
		}
	}

	
	public User addUser(User user, int roleId) throws Exception{
		
		Role role = roleRepository.findById(roleId).orElseThrow(()-> new IDNotFoundException("Role ID Not Found"));
		user.setRole(role);
		if(contactIsValid.isValidContact(user.getContact())){

		return userRepository.save(user);
		} else { throw new ContactInValidException("Enter Valid Contact Number"); }
		
		
}
	
	public User getUser(User user) throws Exception {
		
		if(user != null)//Handle Exception here
		{
		int id=user.getPid();
		return userRepository.findById(id).orElseThrow(()-> new IDNotFoundException("User are Not Present "));
		}
		else {
			throw new LogInNotFoundException("You Can't Login.You Need To Login First");
		}
		
		
	}

	
	public User updateUser(@Valid User userNew,User userOld) throws Exception {
		if(userOld != null) {
		
			userOld.setContact(userNew.getContact());
			userOld.setEmail(userNew.getEmail());
			userOld.setName(userNew.getName());
			userOld.setPassword(userNew.getPassword());
			userOld.setUserName(userNew.getUserName());
			
			return  userRepository.save(userOld);
		}
		else {
			throw new LogInNotFoundException("You Can't Login.You Need To Login First");
		}
	}


	public String getPass(String email, String name) throws Exception {
		
		User user = userRepository.findByEmail(email);
		if(user != null) {
			if(user.getName().equals(name)) {
			return user.getPassword();
			}else {
				throw new NameNotFoundException("Name Not Found ");
			}
		}else {
			throw new NameNotFoundException("Email is not present Please Enter correct Email");
		}
	}
	
	
}	

	



	

	



	


	


	


	

	


