package com.example.Dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignupDto {
      
	private String name;
	private String email;
	private String password;
}
