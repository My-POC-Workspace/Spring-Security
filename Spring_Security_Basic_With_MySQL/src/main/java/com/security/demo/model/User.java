package com.security.demo.model;

import java.util.Set;

public class User {
 
 private Long id;
 private String username;
 private String email;
 private String password;
// @ManyToMany(fetch = FetchType.EAGER)
// @JoinTable(name = "user_roles",
// joinColumns = @JoinColumn(name = "user_id"),
// inverseJoinColumns = @JoinColumn(name = "role_id")
// )
}
 