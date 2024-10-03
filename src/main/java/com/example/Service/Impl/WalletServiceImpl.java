package com.example.Service.Impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Entity.Ride;
import com.example.Entity.User;
import com.example.Entity.Wallet;
import com.example.Entity.WalletTransaction;
import com.example.Entity.Enum.TransactionMethod;
import com.example.Entity.Enum.TransactionType;
import com.example.Service.WalletService;
import com.example.Service.WalletTransactionService;
import com.example.repository.WalletRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService{
	
	private final WalletRepository walletRepository;
	private final WalletTransactionService walletTransactionService;
	
	@Override
	@Transactional
	public Wallet addMoneyToWallet(User user, Double amount,String transactionId,Ride ride,TransactionMethod transactionMethod) {
	      Wallet wallet=findByUser(user);	  
	      wallet.setBalance(wallet.getBalance()+amount);
	      
	      WalletTransaction walletTransaction=WalletTransaction.builder()
	    		                                 .transactionId(transactionId)
	    		                                 .ride(ride)
	    		                                 .wallet(wallet)
	    		                                 .transactionType(TransactionType.CREDIT)
	    		                                 .transactionMethod(transactionMethod)
	    		                                 .amount(amount)
	    		                                 .build();
	      walletTransactionService.createWalletTransaction(walletTransaction);
	      
		return walletRepository.save(wallet);
	}
     
	@Override
	@Transactional
	public Wallet deductMoneyFromWallet(User user, Double amount,String transactionId,Ride ride,TransactionMethod transactionMethod) {
		Wallet wallet=findByUser(user);
		  
	      wallet.setBalance(wallet.getBalance()-amount);
	      
	      WalletTransaction walletTransaction=WalletTransaction.builder()
                  .transactionId(transactionId)
                  .ride(ride)
                  .wallet(wallet)
                  .transactionType(TransactionType.DEBIT)
                  .transactionMethod(transactionMethod)
                  .amount(amount)
                  .build();
//          walletTransactionService.createWalletTransaction(walletTransaction);
	      wallet.getTransaction().add(walletTransaction);
		  return walletRepository.save(wallet);
	}
	
	@Override
	public void withdrawAllMyMoneyFromWallet() {
		
		
	}

	@Override
	public Wallet FindWalletById(Long walletId) {
		walletRepository.findById(walletId)
		                 .orElseThrow(()-> new RuntimeException("WalletId Is not found "+walletId));
		return null;
	}

	@Override
	public Wallet createNewWallet(User user) {
		Wallet wallet=new Wallet();
		wallet.setUser(user);
	
		return walletRepository.save(wallet);
	}

	@Override
	public Wallet findByUser(User user) {
		Wallet wallet=walletRepository.findByUser(user)
				.orElseThrow(()->new RuntimeException("Wallet is not found for user with id"+user.getId()));
		return walletRepository.save(wallet);
	}

	

	

}
