package com.example.Service;

import com.example.Entity.Payment;
import com.example.Entity.Ride;
import com.example.Entity.Enum.PaymentStatus;

public interface PaymentService {
     
	void processPayment(Ride ride);
	
	Payment createNewPayment(Ride ride);
	
	void updatePaymentStatus(Payment payment,PaymentStatus status);
	
}
