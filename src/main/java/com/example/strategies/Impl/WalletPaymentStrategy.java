package com.example.strategies.Impl;



import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Entity.Driver;
import com.example.Entity.Payment;
import com.example.Entity.Ride;
import com.example.Entity.Rider;
import com.example.Entity.Enum.PaymentStatus;
import com.example.Entity.Enum.TransactionMethod;
import com.example.Service.PaymentService;
import com.example.Service.WalletService;
import com.example.repository.PaymentRepository;
import com.example.strategies.PaymentStrategy;

import lombok.RequiredArgsConstructor;

//Rider has 323, Driver had 500
//ride cost is 100,commission=30
//Rider -> 232-100=132
//Driver ->500+(100-30)=570

@Service
@RequiredArgsConstructor
public class WalletPaymentStrategy implements PaymentStrategy{
	
	private final WalletService walletService;
	private final PaymentRepository paymentRepository;
	
	@Override
	@Transactional
	public void processPayment(Payment payment) {
		Driver driver=payment.getRide().getDriver();
		Rider rider=payment.getRide().getRider();
		
		walletService.deductMoneyFromWallet(rider.getUser(),payment.getAmount(),null,payment.getRide(),TransactionMethod.RIDE);
		
		double driverCut=payment.getAmount()*(1-platform_Commission);
		
		walletService.addMoneyToWallet(driver.getUser(),driverCut,null,payment.getRide(),TransactionMethod.RIDE);
		
//		paymentService.updatePaymentStatus(payment, PaymentStatus.CONFIRMED);
		
		payment.setPaymentStatus(PaymentStatus.CONFIRMED);
		paymentRepository.save(payment);
	}

	

}
