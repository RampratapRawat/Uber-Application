package com.example.Service.Impl;

import org.springframework.stereotype.Service;

import com.example.Entity.RideRequest;
import com.example.Service.RideRequestService;
import com.example.repository.RideRequestRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RideRequestServiceImpl implements RideRequestService{
    
	private final RideRequestRepository rideRequestRepository;
	
	@Override
	public RideRequest findRideRequestById(Long rideRequestId) {
		return rideRequestRepository.findById(rideRequestId)
				.orElseThrow(()-> new NullPointerException("rideRequest not found"+rideRequestId));
	}

	@Override
	public void update(RideRequest rideRequest) {
		rideRequestRepository.findById(rideRequest.getId())
				.orElseThrow(()->new NullPointerException("RideRequest not found with id"+rideRequest.getId()));
		rideRequestRepository.save(rideRequest);
	}

}
