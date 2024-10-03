package com.example.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Entity.User;
import com.example.Entity.Wallet;

public interface WalletRepository extends JpaRepository<Wallet,Long> {

	Optional<Wallet> findByUser(User user);


}
