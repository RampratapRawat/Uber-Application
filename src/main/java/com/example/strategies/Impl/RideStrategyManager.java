package com.example.strategies.Impl;

import java.time.LocalTime;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.example.Entity.RideRequest;
import com.example.strategies.DriverMatchingStrategy;
import com.example.strategies.RideFareCalculationStrategy;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor

public class RideStrategyManager {
       
	private final DriverMatchingHighestRatedDriverStrategy highestRatedDriverStrategy;
	private final DriverMatchingNearestDriverStrategy nearestDriverStrategy;
	private final RiderFareDefaultFareCalculationStrategy defaultFareCalculationStrategy;
	private final RideFareSurgePricingFareCalculationStrategy surgePricingFareCalculationStrategy;
	
	public DriverMatchingStrategy driverMatchingStrategy(double riderRating) {
		if(riderRating >=4.8) {
			return highestRatedDriverStrategy;
		}else {
			return nearestDriverStrategy;
		}
	}
	
	public double rideFareCalculationStrategy(RideRequest rideRequest) {
		LocalTime surgeStartTime=LocalTime.of(18, 0);
		LocalTime surgeEndTime=LocalTime.of(21, 0);
		LocalTime currentTime=LocalTime.now();
		
		boolean isSurgeTime=currentTime.isAfter(surgeStartTime)&& currentTime.isBefore(surgeEndTime);
		
		if(isSurgeTime) {
			return surgePricingFareCalculationStrategy.calculateFare(rideRequest);
		}else {
			return defaultFareCalculationStrategy.calculateFare(rideRequest);
		}
	}
}
