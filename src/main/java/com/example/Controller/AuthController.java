package com.example.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Dto.DriverDto;
import com.example.Dto.LoginRequestDto;
import com.example.Dto.LoginResponseDto;
import com.example.Dto.OnboardDriveDto;
import com.example.Dto.SignupDto;
import com.example.Dto.UserDto;
import com.example.Service.AuthService;

import lombok.Data;

@RestController
@RequestMapping(name="/auth")
@Data
public class AuthController {
     private final AuthService authService;
     
     @PostMapping("/signup")
     public ResponseEntity<UserDto> signUp(@RequestBody SignupDto signUpDto) {
    	 return new ResponseEntity<>(authService.signup(signUpDto),HttpStatus.CREATED);
     }
     
     @PostMapping("/onBoardnewDrive/{userid}")
     public ResponseEntity<DriverDto> onBoardnewDrive(@PathVariable Long userid,@RequestBody OnboardDriveDto onboardDriverDto ){
    	 return new ResponseEntity<>(authService.onBoardNewDriver(userid,onboardDriverDto.getVehicleId()),HttpStatus.CREATED);
     }
     
     @PostMapping("/login")
     public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto){
    	 String tokens[]=authService.login(loginRequestDto.getEmail(), loginRequestDto.getPassword());
    	 return ResponseEntity.ok(new LoginResponseDto(tokens[0]));
     }
}
