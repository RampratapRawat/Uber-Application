package com.example.Service;

import org.springframework.data.domain.*;

import com.example.Dto.RideRequestDto;
import com.example.Entity.Driver;
import com.example.Entity.Ride;// this class only handle complete rider process
import com.example.Entity.RideRequest;
import com.example.Entity.Rider;
import com.example.Entity.Enum.RideStatus;

public interface RideService {
     
	Ride getRideById(Long rideId);
	
	void matchWithDrivers(RideRequestDto rideRequestDto);
	
	Ride createNewRide(RideRequest rideRequest,Driver driver);
	
	Ride updateRideStatus(Ride ride,RideStatus rideStatus);
	
	Page<Ride> getAllRidesOfRider(Rider rider, PageRequest pageRequest);
	
	Page<Ride> getAllRidesOfDriver(Driver driver, PageRequest pageRequest);

	
}
