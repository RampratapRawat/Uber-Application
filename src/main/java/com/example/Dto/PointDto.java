package com.example.Dto;

import lombok.*;

@Data
@NoArgsConstructor

public class PointDto {
       
	private double[] coordinates;
	private String type= "Point";
	
	public PointDto(double[] coordinates) {
		super();
		this.coordinates = coordinates;
		
	}
	
}
