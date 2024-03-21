package com.jwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.customexception.UserNotFound;
import com.jwt.helper.JwtUtil;
import com.jwt.model.AuthenticationRequest;
import com.jwt.model.JwtResponse;
import com.jwt.model.UserRegistration;
import com.jwt.repo.RoleRepository;
import com.jwt.repo.UserRepository;
import com.jwt.service.CustomUserDetailsService;

@RestController
public class AdminController {
	
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
	
	
	@PostMapping("/genToken")
	public ResponseEntity<?> generateToken(@RequestBody AuthenticationRequest userPayload ) throws UserNotFound{
//		try {
			
			this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userPayload.getUsername(), userPayload.getPassword()));
//		} catch (Exception e) {
//			throw new UserNotFound("User not found...");
//		}
		
		// If error occurs above, method will return exception and code rest code will not work. So if there is not error, below code will work fine...
		UserDetails userDetail = this.customUserDetailsService.loadUserByUsername(userPayload.getUsername());
		
		String token = jwtUtil.generateToken(userDetail);

		System.out.println("JWT Token --> " + token);
		
		return ResponseEntity.ok(new JwtResponse(token));  // this will automatically get converted to token...
		
	}

}
