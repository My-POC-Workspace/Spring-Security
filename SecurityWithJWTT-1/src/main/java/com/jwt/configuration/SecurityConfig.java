//package com.jwt.configuration;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//	
//	
//	@Autowired
//	UserDetailsService userDetailsService;
//	
//	@Autowired
//	PasswordEncoder passwordEncoder;
//	
//	@Bean
//	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
//		
//		//http configuration
//		/*
//		 
//		http
//		.authorizeHttpRequests() // without user id and password we cant access anything it restrict us.
//		.antMatchers("/**") //matching url
//		.permitAll(); 
//		return http.build();
//		
//		*/
//		
//		http
//		 .cors()  //cross origin resource shared --> check the validity of url just aise hii
//		 .and() // and logical
//		 .csrf() // cross site request forgery --> whether the request is secure (genuine http request (safe from attacker or not))
//		 .disable() // if not safe then disable the csrf 
//		 .authorizeRequests() //valid request then come to authorize that request
//		 .antMatchers("/") // this is used define url
//		 .permitAll() // everyone can be access this url
//		 .antMatchers("/secured") // another url defining
//		 .hasAnyRole("USER", "ADMIN") // above url can be accessed by both user and admin
//		 .antMatchers("/admin") // another url
//		 .hasRole("ADMIN") //only ADMIN will access this
//		 .and() //logical and
//		 .formLogin() //--> Spring will provide basic login form 
//		 //.loginPage("/login") 	// --> for custom login page we need to add it after .formLogin()... 
//		 // If we have custom login page then we need to add 
//		 .and()//logical and
//		 //.logout() --> spring will provide basic logout button..
//		 .httpBasic(); //basic security provider
//		 return http.build();
//	}
//	
//	
//	@Bean // We are telling to authentication provider that we are using this type of authentication 
//	public DaoAuthenticationProvider authenticationProvider(){
//	 DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
//	 authenticationProvider.setUserDetailsService(userDetailsService);
//	 authenticationProvider.setPasswordEncoder(passwordEncoder);
//	 return authenticationProvider;
//	}
//	
//	//following bean method creates a user with username test and password with USER role.
//	
//	/*
//	
//	@Bean
//	public UserDetailsService userDetailService() {
//		UserDetails user = User.withDefaultPasswordEncoder()
//				.username("test")
//				.password("password")
//				.roles("USER")
//				.build();
//		return new InMemoryUserDetailsManager(user);
//		
//	}
//	*/
//
//}
