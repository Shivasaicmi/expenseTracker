package com.expenseTracker.backend.repositories;

import com.expenseTracker.backend.entities.UserRoomsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRoomsRepository extends JpaRepository<UserRoomsEntity,Long> {

    Optional<UserRoomsEntity> findByRoomIdAndUserId(Long roomId,Long userId);

}
