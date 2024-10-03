package com.example.Entity;

import org.locationtech.jts.geom.Point;

import jakarta.persistence.*;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.*;

@Getter
@Setter
@Entity
@Table(indexes = {@Index(name="idx_vehicle_id",columnList="vehicleId")})
@Builder
public class Driver {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
	@JoinColumn(name="user_id")
	private User user;
	
	private Double rating;
	
	private Boolean available;
	
	private String vehicleId;
	
//	@Column(columnDefinition="GeoMetry(POINT,4236)")
	@Column(columnDefinition = "POINT")
	private Point current_location;
}
