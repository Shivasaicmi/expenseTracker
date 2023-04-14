package com.expenseTracker.backend.repositories;

import com.expenseTracker.backend.entities.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomsRepository extends JpaRepository<RoomEntity,Long> {

}
