package com.example.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Entity.Driver;
import com.example.Entity.Rating;
import com.example.Entity.Ride;
import com.example.Entity.Rider;

public interface RatingRepository extends JpaRepository<Rating,Long> {
     List<Rating> findByRider(Rider rider);
     List<Rating> findByDriver(Driver driver);
     Optional<Rating> findByRide(Ride ride);
}
