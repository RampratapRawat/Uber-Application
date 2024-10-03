package com.example.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Entity.Driver;
import com.example.Entity.Ride;
import com.example.Entity.Rider;

public interface RideRepository extends JpaRepository<Ride,Long> {

	Page<Ride> findByRider(Rider rider, PageRequest pageRequest);

	Page<Ride> findByDriver(Driver driver, PageRequest pageRequest);

	

}
