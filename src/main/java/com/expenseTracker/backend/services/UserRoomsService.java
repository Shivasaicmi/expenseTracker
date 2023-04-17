package com.expenseTracker.backend.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expenseTracker.backend.entities.UserRoomsEntity;
import com.expenseTracker.backend.repositories.UserRoomsRepository;

import jakarta.transaction.Transactional;

@Service
public class UserRoomsService {
	
	private UserRoomsRepository userRoomsRepository;

	@Autowired
	public UserRoomsService(UserRoomsRepository userRoomsRepository) {
		this.userRoomsRepository = userRoomsRepository;
	}
	
	@Transactional
	public void deleteUserFromRoom(long userId,long roomId) throws Exception {
		Optional<UserRoomsEntity> userRooms = userRoomsRepository.findByRoomIdAndUserId(roomId, userId);
		if(userRooms.isPresent())
			userRoomsRepository.deleteUserFromRoom(userId, roomId);
		else {
			throw new Exception("User with id "+userId+" does not belong to roomId "+roomId);
		}
	}

}
