package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Entity.WalletTransaction;

public interface WalletTransactionRepository extends JpaRepository<WalletTransaction,Long> {

}
