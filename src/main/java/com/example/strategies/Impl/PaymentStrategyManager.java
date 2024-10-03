package com.example.strategies.Impl;

import org.springframework.stereotype.Component;

import com.example.Entity.Enum.PaymentMethod;
import com.example.strategies.PaymentStrategy;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PaymentStrategyManager {
    
	private final CODPaymentStrategy cashPaymentStrategy;
	private final WalletPaymentStrategy walletPaymentStrategy;
	
	public PaymentStrategy paymentStrategy(PaymentMethod paymentMethod) {
		return switch(paymentMethod) {
		case WALLET -> walletPaymentStrategy;
		case CASH -> cashPaymentStrategy;
		};
	}
}
