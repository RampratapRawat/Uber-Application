package com.example.repository;

import java.util.List;

import org.locationtech.jts.geom.Point;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.Entity.Driver;

//ST_Distance(point1,point2)
//ST_DWithin(point1,10000)
// these two method autoconfig inside sql so no need to add any thing it will run without error


public interface DriverRepository extends JpaRepository<Driver,Long> {
    
	@Query(value="Select d.*,ST_Distance(d.current_location, :pickUpLocation) AS distance"+
	         "From driver d where d.available=true AND ST_DWithin(d.current_location,:pickUplocation,10000)"+
			"ORDER BY distance"+"LIMIT 10",nativeQuery= true)
	List<Driver> findTenNearestDrivers(Point pickUpLocation);

    @Query(value="SELECT d.*" + "FROM driver d" +
                 "WHERE d.available=true AND ST_DWithin(d.current_location, :pickUpLocation ,15000"+
                 "ORDER BY d.rating DESC"+"LIMIT 10",nativeQuery= true)
    List<Driver> findTenNearByTopRatedDrivers(Point pickUpLocation);

}
