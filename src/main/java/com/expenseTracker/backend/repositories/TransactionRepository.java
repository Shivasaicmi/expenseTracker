package com.expenseTracker.backend.repositories;

import com.expenseTracker.backend.entities.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity,Long> {
    Optional<List<TransactionEntity>> findByUserId(Long userId);

    @Query(
            nativeQuery = true,
            value = "SELECT * FROM transactions WHERE userId = :userId AND room_id=:roomId"
    )
    Optional<List<TransactionEntity>> findByUseridAndRoomid(@Param("userId") Long userId,@Param("roomId") Long roomId);

}
