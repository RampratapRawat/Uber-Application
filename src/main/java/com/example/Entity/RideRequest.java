package com.example.Entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.locationtech.jts.geom.Point;

import com.example.Entity.Enum.PaymentMethod;
import com.example.Entity.Enum.RideRequestStatus;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(indexes = {@Index(name="idx_rider_Request",columnList="rider_id")}) 
public class RideRequest {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
     private Long id;
	
	@Column(columnDefinition="POINT")
     private Point pickUpLocation;
	
	@Column(columnDefinition="POINT")
     private Point dropOffLocation;
     
     @CreationTimestamp
     private LocalDateTime requestTime;
     
     @ManyToOne(fetch=FetchType.LAZY)
     private Rider rider;
     
     @Enumerated(EnumType.STRING)
     private PaymentMethod paymentMethod;
     
     @Enumerated(EnumType.STRING)
     private RideRequestStatus rideRequestStatus;
     
     private Double fare;
}
