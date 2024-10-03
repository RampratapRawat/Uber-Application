package com.example.Service;

import com.example.Dto.DriverDto;
import com.example.Dto.SignupDto;
import com.example.Dto.UserDto;

public interface AuthService {
      String[] login(String email,String password);
      
      UserDto signup(SignupDto signupDto);
      
      DriverDto onBoardNewDriver(Long userid, String vehicleId);
}
