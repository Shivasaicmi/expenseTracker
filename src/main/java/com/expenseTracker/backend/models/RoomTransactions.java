package com.expenseTracker.backend.models;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Value;

public interface RoomTransactions {
	
	@Value("#{target.transactionid}")
	Long getTransactionId();
	
	@Value("#{target.title}")
	String getTitle();
	
	@Value("#{target.description}")
	String getDescription();
	
	@Value("#{target.price}")
	double getPrice();
	
	@Value("#{target.created_on}")
	LocalDateTime getCreatedOn();
	
	@Value("#{target.added_on}")
	LocalDateTime getAddedOn();
	
	@Value("#{target.category}")
	String getCategory();
	
	@Value("#{target.username}")
	String getUserName();
}
