package com.example.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DriverDto {
    
	private Long id;
	private UserDto user;
	private Integer rating;
	private Boolean available;
	private String vehicleId;
}
