package com.expenseTracker.backend.services;

import com.expenseTracker.backend.entities.TransactionEntity;
import com.expenseTracker.backend.entities.UserRoomsEntity;
import com.expenseTracker.backend.repositories.RoomsRepository;
import com.expenseTracker.backend.repositories.TransactionRepository;
import com.expenseTracker.backend.repositories.UserRoomsRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    private TransactionRepository transactionRepository;
    private UserRoomsRepository userRoomsRepository;

    private RoomsRepository roomsRepository;

    public TransactionService(TransactionRepository transactionRepository,UserRoomsRepository userRoomsRepository,RoomsRepository roomsRepository){
        this.transactionRepository = transactionRepository;
        this.userRoomsRepository = userRoomsRepository;
        this.roomsRepository = roomsRepository;
    }

    public TransactionEntity addTransaction(TransactionEntity newTransaction){
        newTransaction.setCreatedOn(LocalDateTime.now());
        TransactionEntity addedTransaction =  transactionRepository.save(newTransaction);
        return addedTransaction;
    }

    public void deleteTransactionById(Long transactionId){
        transactionRepository.deleteById(transactionId);
    }

    public List<TransactionEntity> getTransactionsByUserId(Long userId)throws Exception{
        Optional<List<TransactionEntity>> result = transactionRepository.findByUserId(userId);
        if(result.isPresent()){
            return result.get();
        }
        else{
            throw new Exception("this user doesnot have any transactions");
        }
    }

    @Transactional
    public void addTransactionByRoomId(TransactionEntity transactionEntity,Long roomId) throws Exception {
        Optional<UserRoomsEntity> legitUserRelation = userRoomsRepository.findByRoomIdAndUserId(roomId,transactionEntity.getUserId());
        if(legitUserRelation.isPresent()){
            transactionEntity.setRoomId(roomId);
            transactionEntity.setCreatedOn(LocalDateTime.now());
            transactionRepository.save(transactionEntity);
            roomsRepository.updateExpenditureById(roomId,transactionEntity.getPrice());
        }
        else{
            throw new Exception("user can't add transaction he does not belong to this room");
        }

    }

}
