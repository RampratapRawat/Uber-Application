package com.example.Service;

import com.example.Entity.RideRequest;

public interface RideRequestService {
        RideRequest findRideRequestById(Long rideRequestId) ;
        	
        void update(RideRequest rideRequest);
}
