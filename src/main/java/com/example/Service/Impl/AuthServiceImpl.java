package com.example.Service.Impl;


import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Dto.DriverDto;
import com.example.Dto.SignupDto;
import com.example.Dto.UserDto;
import com.example.Entity.Driver;
import com.example.Entity.User;
import com.example.Entity.Enum.Role;
import com.example.Exception.RuntimeConflictException;
import com.example.Service.AuthService;
import com.example.Service.DriverService;
import com.example.Service.RiderService;
import com.example.Service.WalletService;
import com.example.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{
      
	private final ModelMapper modelMapper;
	private final UserRepository userRepository;
    private final RiderService riderService;
    private final DriverService driverService;
    private final WalletService walletService;
    private final PasswordEncoder passwordEncoder;
	
	@Override
	public String[] login(String email, String password) {
		String tokens[]=new String[2];
		return tokens;
	}

	@Override
	@Transactional
	public UserDto signup(SignupDto signupDto) {
		User user=userRepository.findByEmail(signupDto.getEmail()).orElse(null);
		if(user !=null) {
			
			 throw new RuntimeConflictException("cannot signup,this email is already exist"+signupDto.getEmail());
		}
		User mappedUser=modelMapper.map(signupDto, User.class);
		mappedUser.setRoles(Set.of(Role.RIDER));
		mappedUser.setPassword(passwordEncoder.encode(mappedUser.getPassword()));
		User savedUser=userRepository.save(mappedUser);
		
		//TODO create user related entities
		riderService.createNewRider(savedUser);
		//TODO add wallet related service here
		walletService.createNewWallet(savedUser);
		
		return modelMapper.map(savedUser, UserDto.class);
	}

	@Override
	public DriverDto onBoardNewDriver(Long userId,String vechicleId) {
		
		User user=userRepository.findById(userId).orElseThrow(()->new RuntimeException("user is found "+userId));
		
		if(user.getRoles().contains(Role.DRIVER))
			throw new RuntimeConflictException("User with id"+userId+"is already a Driver");
		
		Driver createDriver=Driver.builder()
				                  .user(user)
				                  .rating(0.0)
				                  .vehicleId(vechicleId)
				                  .available(true)
				                  .build();
		user.getRoles().add(Role.DRIVER);
	    userRepository.save(user);
		Driver savedDriver=driverService.onBoardNewDrive(createDriver);
		
		return modelMapper.map(savedDriver,DriverDto.class);
	}

	

}
