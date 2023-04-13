package com.expenseTracker.backend.entities;

import jakarta.persistence.*;

@Entity
@Table(name="split_bill_entity")
public class SplitBillEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="bill_id")
    private Long billid;

    @Column(name="title")
    private String title;

    @Column(name="totalprice")
    private Double totalPrice;

    @Column(name = "owner_id")
    private Long ownerId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="owner_id",updatable = false,insertable = false)
    private UserEntity user;


}
