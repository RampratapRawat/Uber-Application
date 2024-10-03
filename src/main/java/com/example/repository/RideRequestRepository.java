package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Entity.RideRequest;

public interface RideRequestRepository extends JpaRepository<RideRequest,Long> {

}
