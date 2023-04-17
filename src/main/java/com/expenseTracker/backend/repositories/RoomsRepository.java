package com.expenseTracker.backend.repositories;

import com.expenseTracker.backend.entities.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomsRepository extends JpaRepository<RoomEntity,Long> {

    @Modifying
    @Query(
            value = " UPDATE room SET expenditure = expenditure + :value where id = :roomId",
            nativeQuery = true
    )
    void updateExpenditureById( @Param("roomId") Long id, @Param("value") Double value);
    
    @Modifying
    @Query(
    		value = "Update room set expenditure = 0 where id = :roomId",
    		nativeQuery = true
    )
    void refreshExpenditure(@Param("roomId") long roomId);
}
