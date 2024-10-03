package com.example.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.example.Dto.DriverDto;
import com.example.Dto.RideDto;
import com.example.Dto.RideRequestDto;
import com.example.Dto.RiderDto;
import com.example.Entity.Rider;
import com.example.Entity.User;

public interface RiderService {
          
    RideRequestDto requestRide(RideRequestDto rideRequestDto);
	
	RideDto cancelRide(Long rideId);
	
	DriverDto rateDriver(Long rideId,Integer rating);//get rating in stars
	
    RiderDto getMyProfile();//get driver profile
    
    Page<RideDto> getAllMyRides(PageRequest pageRequest);
    
    Rider createNewRider(User user);
    
    Rider getCurrentRider();
}
