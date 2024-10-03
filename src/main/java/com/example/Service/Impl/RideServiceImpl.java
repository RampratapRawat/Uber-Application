package com.example.Service.Impl;


import java.util.Random;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.Dto.RideRequestDto;
import com.example.Entity.Driver;
import com.example.Entity.Ride;
import com.example.Entity.RideRequest;
import com.example.Entity.Rider;
import com.example.Entity.Enum.RideRequestStatus;
import com.example.Entity.Enum.RideStatus;
import com.example.Service.RideRequestService;
import com.example.Service.RideService;
import com.example.repository.DriverRepository;
import com.example.repository.RideRepository;
import com.example.repository.RiderRepository;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class RideServiceImpl implements RideService{
	
	private final RideRepository rideRepository;
	private final RideRequestService rideRequestService;
	private final ModelMapper modelMapper;
	private final RiderRepository riderRepository;
	private final DriverRepository driverRepository;
	

	@Override
	public Ride getRideById(Long rideId) {
		return rideRepository.findById(rideId).orElseThrow(()-> new RuntimeException("ride not found with this id:"+rideId));
	}

	@Override
	public void matchWithDrivers(RideRequestDto rideRequestDto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Ride createNewRide(RideRequest rideRequest, Driver driver) {
		rideRequest.setRideRequestStatus(RideRequestStatus.CONFIRMED);
		Ride ride=modelMapper.map(rideRequest, Ride.class);
		ride.setRideStatus(RideStatus.CONFIRMED);
		ride.setDriver(driver);
		ride.setOtp(generateRandomOTP());
		ride.setId(null);
		
		rideRequestService.update(rideRequest);
		return rideRepository.save(ride);
	}

	

	@Override
	public Ride updateRideStatus(Ride ride, RideStatus rideStatus) {
		ride.setRideStatus(rideStatus);
		return rideRepository.save(ride);
	}

	@Override
	public Page<Ride> getAllRidesOfRider(Rider rider, PageRequest pageRequest) {
		return rideRepository.findByRider(rider,pageRequest);
	}

	@Override
	public Page<Ride> getAllRidesOfDriver(Driver driver, PageRequest pageRequest) {
		return rideRepository.findByDriver(driver,pageRequest);
	}

	private String generateRandomOTP() {
		Random random=new Random();
		int otpInt=random.nextInt(10000);
		return String.format("%04d", otpInt);
	}

}
