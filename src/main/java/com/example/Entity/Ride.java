package com.example.Entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.locationtech.jts.geom.Point;

import com.example.Entity.Enum.PaymentMethod;
import com.example.Entity.Enum.RideStatus;
import com.example.Entity.Enum.RideRequestStatus;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(indexes = {@Index(name="idx_rider",columnList="rider_id"),
		          @Index(name="idx_driver",columnList="driver_id")})
public class Ride {
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
     
     @ManyToOne(fetch=FetchType.LAZY)
     private Driver driver;
     
     private String otp;
     
     @Enumerated(EnumType.STRING)
     private PaymentMethod paymentMethod;
     
     @Enumerated(EnumType.STRING)
     private RideStatus rideStatus;
     
     private Double fare;
     private LocalDateTime startedAt;
     private LocalDateTime endedAt;
}
