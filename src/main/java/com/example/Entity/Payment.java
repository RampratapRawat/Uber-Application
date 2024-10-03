package com.example.Entity;



import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.example.Entity.Enum.PaymentMethod;
import com.example.Entity.Enum.PaymentStatus;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name="Payment")
@Builder
public class Payment {
     @Id
     @GeneratedValue(strategy=GenerationType.IDENTITY)
     private Long id;
     
     @Enumerated(EnumType.STRING)
     private PaymentMethod paymentMethod;
     
     @ManyToOne(fetch=FetchType.LAZY)
     private Ride ride;
     
     private Double amount;
     
     @Enumerated(EnumType.STRING)
     private PaymentStatus paymentStatus;
     
     @CreationTimestamp
     private LocalDateTime paymentTime;

}
