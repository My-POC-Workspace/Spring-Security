package com.jwt.model;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

// Custom User Detail Class
public class UserPrincipal implements UserDetails{
	
	
	private Long id;
	
	private String username;
	
	private String email;
	
	private String password;
	
	
	
	public UserPrincipal() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	public static UserPrincipal create(User user) {
		List<GrantedAuthority> authorities = user.getRoles().stream().map(role ->
		 new SimpleGrantedAuthority(role.getRolename().name())
		 ).collect(Collectors.toList());
		 return new UserPrincipal(
		 user.getId(),
		 user.getUsername(),
		 user.getEmail(),
		 user.getPassword(),
		 authorities
		 
		 );
	}
	
	private Collection<? extends GrantedAuthority> authorities;
	
	public UserPrincipal(Long id,String username, String email, String password, 
			Collection<? extends GrantedAuthority> authorities) {
			 this.id = id;
			 this.username = username;
			 this.email = email;
			 this.password = password;
			 this.authorities = authorities;
			 }

	

	public Long getId() {
		 return id;
		 }
		 public String getEmail() {
		 return email;
		 }
		 
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return this.authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

}
