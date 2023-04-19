package com.expenseTracker.backend.models;

import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;

public interface RoomTransactionModel {

    @Value("#{target.transactionId}")
    Long getTransactionId();

    @Value("#{target.category}")
    String getCategory();

    @Value("#{target.description}")
    String getDescription();


    @Value("#{target.price}")
    Double getPrice();

    @Value("#{target.title}")
    String getTitle();


    @Value("#{target.username}")
    String getUsername();


    @Value("#{target.added_on}")
    LocalDateTime getAdded_on();


    @Value("#{target.created_on}")
    LocalDateTime getCreated_on();

}
