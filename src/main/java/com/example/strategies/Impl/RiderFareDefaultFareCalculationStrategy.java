package com.example.strategies.Impl;

import org.springframework.stereotype.Service;


import com.example.Entity.RideRequest;
import com.example.Service.DistanceService;
import com.example.strategies.RideFareCalculationStrategy;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RiderFareDefaultFareCalculationStrategy implements RideFareCalculationStrategy {
    
	private final DistanceService distanceService ;
	
	@Override
	public double calculateFare(RideRequest rideRequest) {
	    double distance=distanceService.calculate(rideRequest.getPickUpLocation(), rideRequest.getDropOffLocation());
		return distance*Ride_Fare_Multiplier;
	}

}
