package com.example.strategies;

import java.util.List;

import com.example.Dto.RideRequestDto;
import com.example.Entity.Driver;
import com.example.Entity.RideRequest;

public interface DriverMatchingStrategy {

	List<Driver> findMatchingDriver(RideRequest rideRequest);
}
