package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.advice.LogInNotFoundException;
import com.model.Role;
import com.model.User;
import com.repository.RoleRepository;

@Service
public class RoleService {
	
	@Autowired
	RoleRepository roleRepository;
	
	
	public Role addRole(Role role,User user) throws Exception {
		if(user != null) {
			
			return roleRepository.save(role);
			}
			else {
				throw new LogInNotFoundException("You Can't Login.You Need To Login First");
			}
	}

	public List<Role> getRole(User user) throws Exception {
		if(user != null) {
			
			return  roleRepository.findAll();
			}
			else {
				throw new LogInNotFoundException("You Can't Login.You Need To Login First");
			}
	}

	//At time register
	public List<Role> getRole() {
		
		return roleRepository.findAll();
	}
}
