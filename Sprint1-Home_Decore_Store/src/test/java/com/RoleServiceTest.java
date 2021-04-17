package com;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.advice.LogInNotFoundException;
import com.model.Role;
import com.model.User;
import com.repository.RoleRepository;
import com.repository.UserRepository;
import com.service.RoleService;
@SpringBootTest
public class RoleServiceTest {
	@Autowired
	RoleService roleService;
	
	@MockBean
	RoleRepository roleRepository;
	@MockBean
	UserRepository userRepository;
	
	@Test
	public void testAaddRole()throws Exception{
		Role role = new Role();
		User user = new User();
		role.setRid(1);
		role.setTitle("Analyst");
		role.setDescription("Admin");
		user.setContact("9932443578");
		user.setEmail("subha@gmail.com");
		user.setName("Subha");
		user.setPassword("12345");
		user.setPid(2);
		user.setRole(role);
		user.setUserName("Testing");
		
		//Optional<User> user1 = Optional.ofNullable(user);
		//Mockito.when(userRepository.findById(1)).thenReturn(user1);
		Mockito.when(roleRepository.save(role)).thenReturn(role);
		assertThat(roleService.addRole(role, user)).isEqualTo(role);
	}
	
	@Test
	public void testgetRole() throws Exception{
		Role role1 = new Role();
		role1.setRid(1);
		role1.setTitle("Analyst");
		role1.setDescription("Admin");
		Role role2 = new Role();
		role2.setRid(2);
		role2.setTitle("Developer");
		role2.setDescription("Customer");
		
		List<Role> list = new ArrayList<Role>();
		list.add(role1);
		list.add(role2);
		Mockito.when(roleRepository.findAll()).thenReturn(list);
		assertThat(roleService.getRole()).isEqualTo(list);
	}
	
	@Test
	public void testgetRoles(){
		Role role1 = new Role();
		role1.setRid(1);
		role1.setTitle("Analyst");
		role1.setDescription("Admin");
		Role role2 = new Role();
		role2.setRid(2);
		role2.setTitle("Developer");
		role2.setDescription("Customer");
		
		List<Role> list = new ArrayList<Role>();
		list.add(role1);
		list.add(role2);
		Mockito.when(roleRepository.findAll()).thenReturn(list);
		assertThat(roleService.getRole()).isEqualTo(list);
	}
}
