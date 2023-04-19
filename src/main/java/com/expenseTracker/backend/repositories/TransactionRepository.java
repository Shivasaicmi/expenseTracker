package com.expenseTracker.backend.repositories;

import com.expenseTracker.backend.entities.TransactionEntity;
import com.expenseTracker.backend.models.RoomTransactions;
import com.expenseTracker.backend.models.RoomTransactions;

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

    @Query(
	    value = "SELECT t.id as transactionid, * FROM transactions t JOIN users u on t.userid = u.id WHERE t.room_id = :roomId",
	    nativeQuery = true
    )
    List<RoomTransactions> getRoomTransactions(@Param("roomId") long roomId);
    
    @Query(
    	value = "SELECT t.id as transactionid, * FROM transactions t JOIN users u on t.userid = u.id WHERE t.room_id = :roomId and t.category = :category",
    	nativeQuery = true
    )
    List<RoomTransactions> getRoomTransactionsByCategory(@Param("roomId") long roomId, @Param("category") String category);

}
