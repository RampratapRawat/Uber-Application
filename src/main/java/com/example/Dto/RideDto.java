package com.example.Dto;

import java.time.LocalDateTime;
import org.locationtech.jts.geom.Point;
import com.example.Entity.Enum.PaymentMethod;
import com.example.Entity.Enum.RideStatus;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class RideDto {
      
	 private Long id;
		
		
	     private PointDto pickUpLocation;
		
		
	     private PointDto dropOffLocation;
	     
	    
	     private LocalDateTime createdTime;
	     
	     
	     private RiderDto rider;
	     
	     
	     private DriverDto driver;
	     
	    
	     private PaymentMethod paymentMethod;
	     
	     
	     private RideStatus rideStatus;
	     
	     private Double fare;
	     private LocalDateTime startedAt;
	     private LocalDateTime endedAt;
}
