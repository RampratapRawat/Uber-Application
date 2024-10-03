package com.example.Service.Impl;

import java.time.LocalDateTime;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.Dto.DriverDto;
import com.example.Dto.RideDto;
import com.example.Dto.RideRequestDto;
import com.example.Dto.RiderDto;
import com.example.Entity.Driver;
import com.example.Entity.Ride;
import com.example.Entity.RideRequest;
import com.example.Entity.Rider;
import com.example.Entity.User;
import com.example.Entity.Enum.RideRequestStatus;
import com.example.Entity.Enum.RideStatus;
import com.example.Service.DriverService;
import com.example.Service.RatingService;
import com.example.Service.RideService;
import com.example.Service.RiderService;
import com.example.repository.RideRequestRepository;
import com.example.repository.RiderRepository;
import com.example.strategies.DriverMatchingStrategy;
import com.example.strategies.RideFareCalculationStrategy;
import com.example.strategies.Impl.RideFareSurgePricingFareCalculationStrategy;
import com.example.strategies.Impl.RideStrategyManager;

import lombok.Data;

import lombok.extern.slf4j.Slf4j;

@Service
@Data
@Slf4j
public class RiderServiceImpl implements RiderService {

    private  ModelMapper modelMapper;
	private RideStrategyManager rideStrategyManager;
	private RideFareCalculationStrategy rideFareCalculationStrategy;
	private  DriverMatchingStrategy driverMatchingStrategy;
	private RideRequestRepository  rideRequestRepository;
	private RiderRepository riderRepository;
	private  RideService rideService;
	private  DriverService driverService;
	private  RatingService ratingService;
	
	public RiderServiceImpl(@Qualifier("rideFareSurgePricingFareCalculationStrategy") RideFareCalculationStrategy rideFareCalculationStrategy) {
        this.rideFareCalculationStrategy = rideFareCalculationStrategy;
		
    }
	
	@Override
	public RideRequestDto requestRide(RideRequestDto rideRequestDto) {
		Rider rider=getCurrentRider();
		
		RideRequest rideRequest=modelMapper.map(rideRequestDto,RideRequest.class);
		rideRequest.setRideRequestStatus(RideRequestStatus.PENDING);
		
		Double fare=rideStrategyManager.rideFareCalculationStrategy(rideRequest);
		rideRequest.setFare(fare);
		
		RideRequest savedRideRequest=rideRequestRepository.save(rideRequest);
		
		List<Driver> drivers=rideStrategyManager.driverMatchingStrategy(rider.getRating()).findMatchingDriver(rideRequest);
		
		return modelMapper.map(savedRideRequest, RideRequestDto.class);
	}

	@Override
	public RideDto cancelRide(Long rideId) {
		Rider rider=getCurrentRider();
		Ride ride = rideService.getRideById(rideId);
        
		if(!rider.equals(ride.getRider())) {
			throw new RuntimeException("Driver cannot start aride as he has not accept it earlior");
		}
		if(!ride.getRideStatus().equals(RideStatus.CONFIRMED)) {
			throw new RuntimeException("ride status is not confirmed hence cannot be started ,status"+ride.getRideStatus());
		}
		
		Ride savedRide=rideService.updateRideStatus(ride, RideStatus.CANCELED);
		driverService.updateDriverAvailability(ride.getDriver(),true);
		
		return modelMapper.map(savedRide,RideDto.class);
		
		
	}

	@Override
	public DriverDto rateDriver(Long rideId, Integer rating) {
		Ride ride=rideService.getRideById(rideId);
		Rider rider=getCurrentRider();
		if(!rider.equals(ride.getRider())) {
			throw new RuntimeException("Rider is not belong to your ride");
		}
		if(!ride.getRideStatus().equals(RideStatus.ENDED)) {
			throw new RuntimeException("ride status is not ENDED yet"+ride.getRideStatus());
		}
		
		DriverDto dau= ratingService.rateDriver(ride,rating);
		
		return dau;
		
	}

	@Override
	public RiderDto getMyProfile() {
		Rider rider=getCurrentRider();
		return modelMapper.map(rider, RiderDto.class);
		
	}

	@Override
	public Page<RideDto> getAllMyRides(PageRequest pageRequest) {
		   Rider currentRider=getCurrentRider();
		   return rideService.getAllRidesOfRider(currentRider,pageRequest)
				   .map(ride ->modelMapper.map(ride,RideDto.class));
		
	}

	@Override
	public Rider createNewRider(User user) {
		Rider rider =Rider.builder().user(user).rating(0.0).build();
		return riderRepository.save(rider);
	}

	@Override
	public Rider getCurrentRider() {
		return riderRepository.findById(1L).orElseThrow(()-> new NullPointerException("pagal hai tu"+1
				));
	}

	

}
