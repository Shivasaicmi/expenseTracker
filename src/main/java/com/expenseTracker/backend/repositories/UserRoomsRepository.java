package com.expenseTracker.backend.repositories;

import com.expenseTracker.backend.entities.UserRoomsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRoomsRepository extends JpaRepository<UserRoomsEntity,Long> {

    Optional<UserRoomsEntity> findByRoomIdAndUserId(Long roomId,Long userId);
    
    //remove user from room
    @Modifying
    @Query(
    	value = "DELETE from user_rooms where user_id = :userId and room_id = :roomId",
    	nativeQuery = true
    )
    void deleteUserFromRoom(@Param("userId") long userId, @Param("roomId") long roomId);

}
