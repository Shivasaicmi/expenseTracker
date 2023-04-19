package com.expenseTracker.backend.services;

import com.expenseTracker.backend.customExceptions.CategoryNotFoundException;
import com.expenseTracker.backend.entities.TransactionEntity;
import com.expenseTracker.backend.entities.UserRoomsEntity;
import com.expenseTracker.backend.models.RoomTransactions;
import com.expenseTracker.backend.models.RoomTransactions;
import com.expenseTracker.backend.repositories.RoomsRepository;
import com.expenseTracker.backend.repositories.TransactionRepository;
import com.expenseTracker.backend.repositories.UserRoomsRepository;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    private TransactionRepository transactionRepository;
    private BudgetService budgetService;
    private UserRoomsRepository userRoomsRepository;

    private RoomsRepository roomsRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository,UserRoomsRepository userRoomsRepository,RoomsRepository roomsRepository, BudgetService budgetService){
        this.transactionRepository = transactionRepository;
        this.userRoomsRepository = userRoomsRepository;
        this.roomsRepository = roomsRepository;
        this.budgetService = budgetService;
    }

    public TransactionEntity addTransaction(TransactionEntity newTransaction){
        newTransaction.setCreatedOn(LocalDateTime.now());
        TransactionEntity addedTransaction =  transactionRepository.save(newTransaction);
        return addedTransaction;
    }
    
    @Transactional
    public TransactionEntity addPersonalTransaction(TransactionEntity transaction) {
    	transaction.setCreatedOn(LocalDateTime.now());
    	TransactionEntity savedTransaction = transactionRepository.save(transaction);
    	if(budgetService.findByUserIdAndCategory(transaction.getUserId(), transaction.getCategory())) {
			long price = (long) (transaction.getPrice()*1L);
			try {
				budgetService.addExpense(transaction.getUserId(), transaction.getCategory(), price);
			}
			catch(CategoryNotFoundException e) {}
		}
    	return savedTransaction;
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
        if(this.isUserBelongsToRoom(roomId,transactionEntity.getUserId())){
            transactionEntity.setRoomId(roomId);
            transactionEntity.setCreatedOn(LocalDateTime.now());
            transactionRepository.save(transactionEntity);
            roomsRepository.updateExpenditureById(roomId,transactionEntity.getPrice());
        }
        else{
            throw new Exception("user can't add transaction he does not belong to this room");
        }

    }

    @Transactional
    public boolean isUserBelongsToRoom(Long roomId,Long userId){
        Optional<UserRoomsEntity> legitUserRelation = userRoomsRepository.findByRoomIdAndUserId(roomId,userId);
        return legitUserRelation.isPresent();
    }

    @Transactional
    public List<TransactionEntity> getTransactionsByRoomId(Long roomId,Long userId) throws Exception {
        if(isUserBelongsToRoom(roomId,userId)){
           Optional<List<TransactionEntity>> transactionEntitiesResult = transactionRepository.findByUseridAndRoomid(userId,roomId);
           if(transactionEntitiesResult.isPresent()){
               return transactionEntitiesResult.get();
           }
           else{
               throw  new Exception("no transactions found");
           }
        }
        else{
            throw new Exception("user doesnot belong to this room");
        }
    }
    
    @Transactional
    public List<RoomTransactions> getTransactionsByRoomIdWithUsername(long roomId) {
    	return transactionRepository.getRoomTransactions(roomId);
    }
    
    
    public List<RoomTransactions> getRoomTransactionsByCatgeory(long roomId, String catgeory) {
    	List<RoomTransactions> roomTransactions = transactionRepository.getRoomTransactionsByCategory(roomId, catgeory);
    	return roomTransactions;
    }

}
