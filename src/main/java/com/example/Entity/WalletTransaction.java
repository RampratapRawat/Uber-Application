package com.example.Entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.example.Entity.Enum.TransactionMethod;
import com.example.Entity.Enum.TransactionType;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name="WalletTransaction")
@Builder
public class WalletTransaction {
	 @Id
     @GeneratedValue(strategy=GenerationType.IDENTITY)
     private Long id;
	 
	 private Double amount;
	 
	 private TransactionType transactionType;
	 
	 private TransactionMethod transactionMethod;
	 
	 @OneToOne
	 private Ride ride;
	 
	 private String transactionId;
	 
	 @CreationTimestamp
	 private LocalDateTime timestamp;
	 
	 @ManyToOne(fetch=FetchType.LAZY)
	 private Wallet wallet;

	
}
