package com.example.Service;

import com.example.Dto.DriverDto;
import com.example.Dto.RiderDto;
import com.example.Entity.Driver;
import com.example.Entity.Ride;
import com.example.Entity.Rider;

public interface RatingService {
       
	 DriverDto rateDriver(Ride ride,Integer rating);
	 RiderDto rateRider(Ride ride,Integer rating);
	 void createNewRating(Ride ride);
}
