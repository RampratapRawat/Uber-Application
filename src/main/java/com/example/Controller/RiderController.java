package com.example.Controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Dto.DriverDto;
import com.example.Dto.RideDto;
import com.example.Dto.RideRequestDto;
import com.example.Dto.RiderDto;
import com.example.Service.RideService;
import com.example.Service.RiderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/rider")
@RequiredArgsConstructor
public abstract class RiderController {
      
	private final RiderService riderService;
	
	@PostMapping("/requestRide")
	public ResponseEntity<RideRequestDto> requestRide(@RequestBody RideRequestDto rideRequestDto) {
		return ResponseEntity.ok(riderService.requestRide(rideRequestDto));
	}
	
	@PostMapping("/cancelRide/{rideId}")
	public ResponseEntity<RideDto> cancelRide(@PathVariable Long rideId) {
		return ResponseEntity.ok(riderService.cancelRide(rideId));
	}
	
	@PostMapping("/driverRide")
	public ResponseEntity<DriverDto> rateDriver(@RequestBody DriverDto driverDto) {
		return ResponseEntity.ok(riderService.rateDriver(driverDto.getId(),driverDto.getRating()));
	}
	
	@GetMapping("/getMyProfile")
	public ResponseEntity<RiderDto> getMyProfile(){
		return ResponseEntity.ok(riderService.getMyProfile());
	}
	
	@GetMapping("/getMyRides")
	public  ResponseEntity<Page<RideDto>> getAllMyRides(@RequestParam (defaultValue="0") Integer pageOffset,
			                                           @RequestParam (defaultValue="10",required=false)Integer pageSize){
	       PageRequest pageRequest = PageRequest.of(pageOffset,pageSize,Sort.by(Sort.Direction.DESC,"creationTime","id"));
	      return ResponseEntity.ok(riderService.getAllMyRides(pageRequest));
	}
	
	
}
