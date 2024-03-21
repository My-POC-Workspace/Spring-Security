package com.jwt.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jwt.model.Role;
import com.jwt.model.Rolename;

public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByrolename(Rolename rolename);
}
