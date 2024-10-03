package com.example.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Entity.Ride;
import com.example.Entity.Rider;

public interface RiderRepository extends JpaRepository<Rider,Long> {

	

}
