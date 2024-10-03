package com.example.Dto;

import java.util.Set;

import com.example.Entity.Enum.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UserDto {
     
	private String name;
	private String email;
	private Set<Role> roles;
}
