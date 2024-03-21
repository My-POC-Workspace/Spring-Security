package com.jwt.controller;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.customexception.UserNotFound;
import com.jwt.helper.JwtUtil;
import com.jwt.model.JwtResponse;
import com.jwt.model.Role;
import com.jwt.model.RoleName;
import com.jwt.model.User;
import com.jwt.model.UserRegistration;
import com.jwt.repo.RoleRepository;
import com.jwt.repo.UserRepository;
import com.jwt.service.CustomUserDetailsService;

@RestController
public class AuthController {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserRepository userRepository;

	@PostMapping("/registerUser")
	public String registerUser(@RequestBody UserRegistration userPayload) throws UserNotFound {
		// Here we are setting user in userpayload because we are using DTO's...
		User user = new User();
		user.setUsername(userPayload.getUsername());
		user.setEmail(userPayload.getEmail());
		// user.setPassword(user.getPassword());
		user.setPassword(passwordEncoder.encode(userPayload.getPassword()));
		// user.setRoles(RoleName.ROLE_USER);

		Role userRole = roleRepository.findByrolename(RoleName.ROLE_USER).get();
		// user.setRoles(roleRepository.findByrolename(RoleName.ROLE_USER).get());
		user.setRoles(Collections.singleton(userRole));
		userRepository.save(user);
		
		 return "Registered Successfully";

	}

	@PostMapping("/registerAdmin")
	public String registerAdmin(@RequestBody UserRegistration adminPayload) {
		User user = new User();
		Role userRole = roleRepository.findByrolename(RoleName.ROLE_ADMIN).get();
		user.setUsername(adminPayload.getUsername());
		user.setEmail(adminPayload.getEmail());
		// user.setPassword(adminPayload.getPassword());
		user.setPassword(passwordEncoder.encode(adminPayload.getPassword()));
		user.setRoles(Collections.singleton(userRole));
		userRepository.save(user);

		return "Registered Successfully";
	}

	

}
