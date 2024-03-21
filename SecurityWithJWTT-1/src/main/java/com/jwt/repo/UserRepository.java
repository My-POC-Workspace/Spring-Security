package com.jwt.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jwt.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	User findByemail(String email);
	User findByusername(String username);
}
