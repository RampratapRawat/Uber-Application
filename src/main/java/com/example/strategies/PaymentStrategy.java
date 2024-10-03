package com.example.strategies;

import com.example.Entity.Payment;
import com.example.Entity.Ride;
import com.example.Entity.Enum.PaymentStatus;

public interface PaymentStrategy {
	
	static final double platform_Commission=0.3; 
    
	void processPayment(Payment payment);
	
	
}
