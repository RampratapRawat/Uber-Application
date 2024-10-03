package com.example.Dto;

import java.time.LocalDateTime;


import org.locationtech.jts.geom.Point;

import com.example.Entity.Rider;
import com.example.Entity.Enum.PaymentMethod;
import com.example.Entity.Enum.RideRequestStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RideRequestDto {
      
    private Long id;
	
	
    private PointDto pickUpLocation;
	
	
    private PointDto dropOffLocation;
    
    
    private LocalDateTime requestTime;
    
    
    private RiderDto rider;
    
    private Double fare;
   
    private PaymentMethod paymentMethod;
    
    
    private RideRequestStatus riderRequestStatus;
}
