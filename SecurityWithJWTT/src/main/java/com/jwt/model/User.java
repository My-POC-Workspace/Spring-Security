package com.jwt.model;

import java.util.Set;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.aspectj.weaver.tools.Trace;
import org.hibernate.annotations.NaturalId;
import org.hibernate.type.TrueFalseType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Users", uniqueConstraints = {
		@UniqueConstraint(columnNames = {
			"username"	
		}),
		@UniqueConstraint(columnNames = {
				"email"
		})
})
//public class User extends DateAudit{

public class User{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotBlank(message = "name should not be blank")
	@Size(max = 40)
	private String name;
	
	@NotBlank
	@Size(max = 40)
	private String username;
	
	@NaturalId // Natural IDs are immutable by default and you should not provide setter methods for them. If you need mutable, natural identifier, you have to set the mutable attribute of the @NaturalId annotation to true.
	@NotBlank
	@Size(max = 30)
	private String email;
	
	@NotBlank
	@Size(max = 100)
	@Column(unique = true)  // Second type of declaring column as unique ...
	private String password;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_roles",										 // To give name to third table
	joinColumns =  @JoinColumn(name = "user_id"),              //  To give name to first column of third table
	inverseJoinColumns = @JoinColumn(name =  "role_id"))  //   To give name to second column of third table
	private Set<Role> role;
	

}
