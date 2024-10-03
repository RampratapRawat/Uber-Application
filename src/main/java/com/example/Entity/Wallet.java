package com.example.Entity;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name="Wallet")
public class Wallet {
     @Id
     @GeneratedValue(strategy=GenerationType.IDENTITY)
     private Long id;
     
     @OneToOne(fetch=FetchType.LAZY,optional=false)
     private User user;
     
     private Double balance=0.0;
     
     @OneToMany(mappedBy="wallet",fetch=FetchType.LAZY)
     private List<WalletTransaction> transaction;
}
