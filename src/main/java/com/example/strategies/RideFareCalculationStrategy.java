package com.example.strategies;

import com.example.Entity.RideRequest;

public interface RideFareCalculationStrategy {
    
	double Ride_Fare_Multiplier=10;
	double calculateFare(RideRequest rideRequest);
}
