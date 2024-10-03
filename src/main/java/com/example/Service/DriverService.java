package com.example.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;


import com.example.Dto.DriverDto;
import com.example.Dto.RideDto;
import com.example.Dto.RiderDto;
import com.example.Entity.Driver;



public interface DriverService {
     
	RideDto acceptRide(Long rideId);
	
	RideDto cancelRide(Long rideId);
	
	RideDto startRide(Long rideId,String otp);
	
	RideDto endRide(Long rideId);
	
	RiderDto rateRider(Long rideId,Integer rating);//get rating in stars
	
    DriverDto getMyProfile();//get driver profile
    
    Page<RideDto> getAllMyRides(PageRequest pageRequest);
    
    Driver getCurrentDriver();
    
    Driver updateDriverAvailability(Driver driver,boolean available);
    
    Driver onBoardNewDrive(Driver driver);
}
