package com.example.strategies.Impl;

import java.util.List;

import org.springframework.stereotype.Service;


import com.example.Entity.Driver;
import com.example.Entity.RideRequest;
import com.example.repository.DriverRepository;
import com.example.strategies.DriverMatchingStrategy;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DriverMatchingNearestDriverStrategy implements DriverMatchingStrategy{

	private final DriverRepository driverRepository;

	@Override
	public List<Driver> findMatchingDriver(RideRequest rideRequest) {
		 return  driverRepository.findTenNearestDrivers(rideRequest.getPickUpLocation());
	}
	


}
