package com.example.Dto;

import java.time.LocalDateTime;

import com.example.Entity.Ride;
import com.example.Entity.Wallet;
import com.example.Entity.Enum.TransactionMethod;
import com.example.Entity.Enum.TransactionType;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class WalletTransactionDto {

     private Long id;
	 
	 private Double amount;
	 
	 private TransactionType transactionType;
	 
	 private TransactionMethod transactionMethod;
	 
	 
	 private Ride ride;
	 
	 private String transactionId;
	 
	
	 private LocalDateTime timestamp;
	 
	
	 private Wallet wallet;
}
