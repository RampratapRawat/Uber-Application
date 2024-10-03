package com.example.Service.Impl;


import org.springframework.stereotype.Service;
import com.example.Entity.WalletTransaction;
import com.example.Service.WalletTransactionService;
import com.example.repository.WalletTransactionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WalletTransactionServiceImpl implements WalletTransactionService{
	
	private final WalletTransactionRepository walletTransactionRepository;
  

    @Override
	public void createWalletTransaction(WalletTransaction walletTransaction) {
		walletTransactionRepository.save(walletTransaction);
		
	}

}
