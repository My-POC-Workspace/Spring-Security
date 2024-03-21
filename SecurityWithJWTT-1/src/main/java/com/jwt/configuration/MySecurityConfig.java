package com.jwt.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.jwt.filter.JwtRequestFilter;
import com.jwt.model.JwtResponse;
import com.jwt.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity  // To Enable the basic web security...
public class MySecurityConfig extends WebSecurityConfigurerAdapter {  // WebSecurityConfigurationAdapter is used to change the default configuratioin and apply our own configuration...

	@Autowired
	private UserDetailsService userDetailsService;
	
//	@Autowired
//	private CustomUserDetailsService customUserDetailsService;
	
	@Autowired
	private JwtRequestFilter jwtRequestFilter;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {  // In this We will define which url is accessible to which person like Admin or User...
		http
		.csrf()  // CROSS SITE REQUEST FORGERY -->  It is an attack that forces enduser to execute unwanted action on a web application in which they're currently authenticated...
		.disable();
//		.cors()
//		.disable()
		http
		.authorizeRequests()
		.antMatchers("/rest/**")
		.authenticated()
		.anyRequest()
		.permitAll()
//		.authenticated()
		.and()
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS); // To manage the state of session...
//		http.addFilterBefore(, null)
		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	}  
	
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//	 http.csrf().disable();
//	 http.authorizeRequests()
//	 .antMatchers("/rest/**")
//	 .authenticated()
//	 .anyRequest()
//	 .permitAll()
//	 .and().exceptionHandling()
//	 .authenticationEntryPoint()
//	 .and().sessionManagement()
//	 .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//	 http.addFilterBefore(jwtRequestFilter 
//	UsernamePasswordAuthenticationFilter.class);
//	}
	
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception { // Using this we can define like which authentication we are going to use like InMemory authentication or DataBAse authentication or JWT authentication...
//		// TODO Auto-generated method stub
//		auth.userDetailsService(customUserDetailsService);  // Until and unless we will not impliments UserDetailsService in CustomUserDetailsService class, it will show error because it expects to return UserDetailsService object
//	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//		auth.authenticationProvider(authenticationProvider());
	}
	
//	@Bean // We are telling to authentication provider that we are using this type of authentication 
//	public DaoAuthenticationProvider authenticationProvider(){
//	 DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
//	 authenticationProvider.setUserDetailsService(customUserDetailsService);
//	 authenticationProvider.setPasswordEncoder(passwordEncoder());
//	 return authenticationProvider;
//	}
	

	@Bean  // By creating a bean, we can easily autowire this and use it's functionality...
	public PasswordEncoder passwordEncoder() {  // Use to encode the password so that if anyone get access to password, they shouldn't able to read it...
		return new  BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	

	
	

}
