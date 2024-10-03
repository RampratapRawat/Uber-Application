package com.example.Service;

import com.example.Entity.Ride;
import com.example.Entity.User;
import com.example.Entity.Wallet;
import com.example.Entity.Enum.TransactionMethod;

public interface WalletService {
      
	Wallet addMoneyToWallet(User user,Double amount,String transactionId,Ride ride,TransactionMethod transactionMethod);
	
	Wallet deductMoneyFromWallet(User user,Double amount,String transactionId,Ride ride,TransactionMethod transactionMethod);
	
	void withdrawAllMyMoneyFromWallet();
	
	Wallet FindWalletById(Long walletId);
	
	Wallet createNewWallet(User user);
	
	Wallet findByUser(User user);
}
