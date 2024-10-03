package com.example.Service.Impl;

import java.time.LocalDateTime;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Dto.DriverDto;
import com.example.Dto.RideDto;
import com.example.Dto.RiderDto;
import com.example.Entity.Driver;
import com.example.Entity.Ride;
import com.example.Entity.RideRequest;
import com.example.Entity.Enum.RideRequestStatus;
import com.example.Entity.Enum.RideStatus;
import com.example.Service.DriverService;
import com.example.Service.PaymentService;
import com.example.Service.RatingService;
import com.example.Service.RideRequestService;
import com.example.Service.RideService;
import com.example.repository.DriverRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService {
     
	private final RideRequestService rideRequestService;
	private final DriverRepository driverRepository;
	private final RideService rideService;
	private final ModelMapper modelMapper;
	private final PaymentService paymentService;
	private final RatingService ratingService;
	
	@Override
	@Transactional
	public RideDto acceptRide(Long rideRequestId) {
		RideRequest rideRequest=rideRequestService.findRideRequestById(rideRequestId);
		
		if(!rideRequest.getRideRequestStatus().equals(RideRequestStatus.PENDING)) {
			throw new RuntimeException("RideRequest cannot be Accepted,status is"+rideRequest.getRideRequestStatus());
		}
		
		Driver currentDriver=getCurrentDriver();
		if(!currentDriver.getAvailable()) {
			throw new RuntimeException("Driver can not accept ride due to unavailability");
		}
		
		Driver savedDriver=updateDriverAvailability(currentDriver,false);
//		currentDriver.setAvailable(false);
//		Driver savedDriver=driverRepository.save(currentDriver);
		Ride ride=rideService.createNewRide(rideRequest, savedDriver);
		return modelMapper.map(ride,RideDto.class);

	}

	@Override
	public RideDto cancelRide(Long rideId) {
		Ride ride=rideService.getRideById(rideId);
		Driver driver=getCurrentDriver();
		if(!driver.equals(ride.getDriver())) {
			throw new RuntimeException("Driver cannot start aride as he has not accept it earlior");
		}
		if(!ride.getRideStatus().equals(RideStatus.CONFIRMED)) {
			throw new RuntimeException("ride status is not confirmed hence cannot be started ,status"+ride.getRideStatus());
		}
		rideService.updateRideStatus(ride, RideStatus.CANCELED);
		updateDriverAvailability( driver,true);
//		driver.setAvailable(true);
//		driverRepository.save(driver);
		return modelMapper.map(ride,RideDto.class);
	}

	@Override
	public RideDto startRide(Long rideId,String Otp) {
		Ride ride=rideService.getRideById(rideId);
		Driver driver=getCurrentDriver();
		if(!driver.equals(ride.getDriver())) {
			throw new RuntimeException("Driver cannot start aride as he has not accept it earlior");
		}
		if(!ride.getRideStatus().equals(RideStatus.CONFIRMED)) {
			throw new RuntimeException("ride status is not confirmed hence cannot be started ,status"+ride.getRideStatus());
		}
		if(Otp.equals(ride.getOtp())) {
			throw new RuntimeException("otp is not valid,otp:"+Otp);
		}
		ride.setStartedAt(LocalDateTime.now());
		Ride savedRide=rideService.updateRideStatus(ride, RideStatus.ONGOING);
		
		paymentService.createNewPayment(savedRide);
		ratingService.createNewRating(savedRide);
		
		return modelMapper.map(savedRide,RideDto.class);
	}

	@Override
	public RideDto endRide(Long rideId) {
		Ride ride=rideService.getRideById(rideId);
		Driver driver=getCurrentDriver();
		
		if(!driver.equals(ride.getDriver())) {
			throw new RuntimeException("Driver cannot start aride as he has not accept it earlior");
		}
		
		if(!ride.getRideStatus().equals(RideStatus.ONGOING)) {
			throw new RuntimeException("ride status is not Ongoing hence cannot be Ended ,status"+ride.getRideStatus());
		}
		ride.setEndedAt(LocalDateTime.now());
		Ride savedRide=rideService.updateRideStatus(ride, RideStatus.ENDED);
		updateDriverAvailability(driver,true);
		paymentService.createNewPayment(ride); 
		return modelMapper.map(savedRide,RideDto.class);
	}

	@Override
	public RiderDto rateRider(Long rideId, Integer rating) {
		Ride ride=rideService.getRideById(rideId);
		Driver driver=getCurrentDriver();
		if(!driver.equals(ride.getDriver())) {
			throw new RuntimeException("Driver is not belong to your ride");
		}
		if(!ride.getRideStatus().equals(RideStatus.ENDED)) {
			throw new RuntimeException("ride status is not ENDED yet"+ride.getRideStatus());
		}
		
		RiderDto dau= ratingService.rateRider(ride,rating);
		
		return dau;
	}

	@Override
	public DriverDto getMyProfile() {
		Driver currentdriver=getCurrentDriver();
		return modelMapper.map(currentdriver, DriverDto.class);
	}

	@Override
	public Page<RideDto> getAllMyRides(PageRequest pageRequest) {
		   Driver currentDriver=getCurrentDriver();
		   return rideService.getAllRidesOfDriver(currentDriver,pageRequest)
				   .map(ride ->modelMapper.map(ride,RideDto.class));
		
	}

	@Override
	public Driver getCurrentDriver() {
		
		return driverRepository.findById(2L).orElseThrow(()-> new RuntimeException("Driver not found with this id"+2));
		
	}

	@Override
	public Driver updateDriverAvailability(Driver driver, boolean available) {
		
	    driver.setAvailable(available);	
	   return driverRepository.save(driver);
		
	}

	@Override
	public Driver onBoardNewDrive(Driver driver) {
		return driverRepository.save(driver);
	}

}
