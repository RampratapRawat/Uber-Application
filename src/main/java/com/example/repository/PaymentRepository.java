package com.example.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Entity.Payment;
import com.example.Entity.Ride;

public interface PaymentRepository extends JpaRepository<Payment,Long> {

	Optional<Payment> findbyRide(Ride ride);

	

}
