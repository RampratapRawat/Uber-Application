package com.example.strategies.Impl;

import org.springframework.stereotype.Service;

import com.example.Entity.Driver;
import com.example.Entity.Payment;
import com.example.Entity.Wallet;
import com.example.Entity.Enum.PaymentStatus;
import com.example.Entity.Enum.TransactionMethod;
import com.example.Service.PaymentService;
import com.example.Service.WalletService;
import com.example.repository.PaymentRepository;
import com.example.strategies.PaymentStrategy;

import lombok.RequiredArgsConstructor;

//100 pay by user
//70 will go to driver and 30 to ola for plateform fee


@Service
@RequiredArgsConstructor
public class CODPaymentStrategy implements PaymentStrategy {
	
	private final WalletService walletService;
	private final PaymentRepository paymentRepository;
	
	 @Override
	public void processPayment(Payment payment) {
		Driver driver=payment.getRide().getDriver();
		
		double platformCommission=payment.getAmount()*platform_Commission;
		
		walletService.deductMoneyFromWallet(driver.getUser(), platformCommission, null, payment.getRide(), TransactionMethod.RIDE);
		
		payment.setPaymentStatus(PaymentStatus.CONFIRMED);
		paymentRepository.save(payment);
	}

}
