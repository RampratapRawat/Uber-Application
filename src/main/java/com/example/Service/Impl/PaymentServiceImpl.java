package com.example.Service.Impl;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Service;

import com.example.Entity.Payment;
import com.example.Entity.Ride;
import com.example.Entity.Enum.PaymentStatus;
import com.example.Service.PaymentService;
import com.example.repository.PaymentRepository;
import com.example.strategies.Impl.PaymentStrategyManager;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
	
	private final PaymentRepository paymentRepository;
	private final PaymentStrategyManager paymentStrategyManager;
	
	@Override
	public void processPayment(Ride ride) {
		Payment payment=paymentRepository.findbyRide(ride)
				                         .orElseThrow(()->new RuntimeException("payment not found for ride with this id"+ride.getId()));
		paymentStrategyManager.paymentStrategy(payment.getPaymentMethod()).processPayment(payment);
		
	}

	@Override
	public Payment createNewPayment(Ride ride) {
		Payment payment = Payment.builder()
				                 .ride(ride)
				                 .paymentMethod(ride.getPaymentMethod())
				                 .amount(ride.getFare())
				                 .paymentStatus(PaymentStatus.PENDING)
				                 .build();
		
		return paymentRepository.save(payment);
	}

	@Override
	public void updatePaymentStatus(Payment payment, PaymentStatus status) {
		payment.setPaymentStatus(status);
		paymentRepository.save(payment);
		
	}

	



}
