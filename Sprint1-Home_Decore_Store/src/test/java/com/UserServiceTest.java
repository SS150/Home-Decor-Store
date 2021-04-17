package com;

import static org.assertj.core.api.Assertions.assertThat;
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

import com.advice.IDNotFoundException;
import com.model.Login;
import com.model.Role;
import com.model.User;
import com.repository.RoleRepository;
import com.repository.UserRepository;
import com.service.UserService;
@SpringBootTest
class UserServiceTest {
	@Autowired
	UserService userService;
	
	@MockBean
	UserRepository userRepository;
	@MockBean
	RoleRepository roleRepository;
	
	@Test
	public void addUserTest() throws Exception {
		
		Role role = new Role();
		role.setDescription("For testing user ");
		role.setTitle("Testing");
		role.setRid(1);
		
		
		
		User user = new User();
		user.setContact("8308327198");
		user.setEmail("tush@gmail.com");
		user.setName("TestRaju");
		user.setPassword("test");
		user.setUserName("Test");
		user.setRole(role);
		user.setPid(2);
		
		Optional<Role> role1=Optional.ofNullable(role);
		Mockito.when(roleRepository.findById(1)).thenReturn(role1);
		Mockito.when(userRepository.save(user)).thenReturn(user);
		
		    
		assertThat(userService.addUser(user,1)).isEqualTo(user);
		   
		
		
	}

	@Test
	public void getUserTest() throws Exception {
		Role role = new Role();
		role.setDescription("For testing user ");
		role.setTitle("Testing");
		role.setRid(1);
		
		
		
		User user = new User();
		user.setContact("8308327198");
		user.setEmail("tush@gmail.com");
		user.setName("TestRaju");
		user.setPassword("test");
		user.setUserName("Test");
		user.setRole(role);
		user.setPid(2);
		
		Optional<User> user1=Optional.ofNullable(user);
		Mockito.when(userRepository.findById(2)).thenReturn(user1);
		assertThat(userService.getUser(user)).isEqualTo(user);
		   
		
	}
	
	@Test
	public void updateUserTest() throws Exception {
		
		Role role = new Role();
		role.setDescription("For testing user ");
		role.setTitle("Testing");
		role.setRid(1);
			
		User user = new User();
		user.setContact("8308327198");
		user.setEmail("tush@gmail.com");
		user.setName("TestRaju");
		user.setPassword("test");
		user.setUserName("Test");
		user.setRole(role);
		user.setPid(2);
		
		User userNew = new User();
		userNew.setContact("8308389555");
		userNew.setEmail("Ramu@gmail.com");
		userNew.setName("Ramu");
		userNew.setPassword("ramu");
		userNew.setUserName("ramu");
		userNew.setRole(role);
		userNew.setPid(2);
		
		Mockito.when(userRepository.save(user)).thenReturn(userNew);
		assertThat(userService.updateUser(userNew,user)).isEqualTo(userNew);
	}
	
	@Test
	public void getLoginTest() throws Exception {
		
		Login login = new Login();
		login.setPassword("test");
		login.setUserName("Test");
		
		Role role = new Role();
		role.setDescription("For testing user ");
		role.setTitle("Testing");
		role.setRid(1);
			
		User user = new User();
		user.setContact("8308327198");
		user.setEmail("tush@gmail.com");
		user.setName("TestRaju");
		user.setPassword("test");
		user.setUserName("Test");
		user.setRole(role);
		user.setPid(2);
		
		User userNew = new User();
		userNew.setContact("8308389555");
		userNew.setEmail("Ramu@gmail.com");
		userNew.setName("Ramu");
		userNew.setPassword("ramu");
		userNew.setUserName("ramu");
		userNew.setRole(role);
		userNew.setPid(3);
		
		User userold = null;
		List<User> list = new ArrayList<User>();
		list.add(user);
		list.add(userNew);
		

		Mockito.when(userRepository.findAll()).thenReturn(list);
		assertThat(userService.getLogin(login,userold)).isEqualTo(user);
		
		
	}

}
