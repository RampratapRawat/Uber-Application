package com.example.Entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(indexes = {@Index(name="idx_rating_rider",columnList="rider_id"),
        @Index(name="idx_rating_driver",columnList="driver_id")})
public class Rating{
      @Id
      @GeneratedValue(strategy=GenerationType.IDENTITY)
      private Long id;
      
      @OneToOne
      private Ride ride;
      
      @ManyToOne
      private Rider rider;
      
      @ManyToOne
      private Driver driver;
      
      private Integer driverRating;
      private Integer riderRating;
}
