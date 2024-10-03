package com.example.Entity;

import java.util.Set;

import com.example.Entity.Enum.Role;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name="app_user",indexes = {@Index(name="idx_email",columnList="email")}) 
public class User {
    
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	@Column(unique=true)
	private String email;
	
	private String password;
	
	@ElementCollection(fetch=FetchType.LAZY)
	@Enumerated(EnumType.STRING)
	private Set<Role> roles;
}
