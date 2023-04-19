package com.expenseTracker.backend.models;

import org.springframework.beans.factory.annotation.Value;

public interface RoomUsers {

	@Value("#{target.room_id}")
	Long getRoomId();
	
	@Value("#{target.username}")
	String getUserName();
}
