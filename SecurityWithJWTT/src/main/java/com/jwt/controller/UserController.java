package com.jwt.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.model.Role;
import com.jwt.model.Rolename;
import com.jwt.model.User;
import com.jwt.repository.RoleRepository;
import com.jwt.repository.UserRepository;

import io.jsonwebtoken.lang.Collections;

@RestController
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@RequestMapping("/test")
	public String test() {
		return "working...";
	}
	
//	@PostMapping("/register")
//	public String registerUser(@RequestBody User user) {
////	 Set<Role> userRole = (Set<Role>) roleRepository.findByrolename(Rolename.ROLE_USER).get();
////		user.setRole(userRole);
////		userRepository.save(user);
//		return "Register Successfully...";
//		
//	}

}
