package com.example.strategies.Impl;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;


import com.example.Entity.RideRequest;
import com.example.Service.DistanceService;
import com.example.strategies.RideFareCalculationStrategy;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Primary
public class RideFareSurgePricingFareCalculationStrategy implements RideFareCalculationStrategy{

    private final DistanceService distanceService ;
    private static final double SURGE_FACTOR=2;
	
	@Override
	public double calculateFare(RideRequest rideRequest) {
	    double distance=distanceService.calculate(rideRequest.getPickUpLocation(), rideRequest.getDropOffLocation());
		return distance*Ride_Fare_Multiplier*SURGE_FACTOR;
	}

}
