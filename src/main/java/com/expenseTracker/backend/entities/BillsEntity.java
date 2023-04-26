package com.expenseTracker.backend.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Table
@Entity(name="bills")
public class BillsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="userid")
    private Long userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="userid",updatable = false,insertable = false)
    private UserEntity user;

    @Column(name="price")
    private Double price;

    @Column(name="expiry_date")
    private LocalDate expiryDate;

}
