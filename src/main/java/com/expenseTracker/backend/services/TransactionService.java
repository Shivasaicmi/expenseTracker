package com.expenseTracker.backend.services;

import com.expenseTracker.backend.customExceptions.CategoryNotFoundException;
import com.expenseTracker.backend.entities.TransactionEntity;
import com.expenseTracker.backend.entities.UserRoomsEntity;

import com.expenseTracker.backend.models.GroupTransactionModel;
import com.expenseTracker.backend.models.RoomTransactionModel;

import com.expenseTracker.backend.repositories.*;
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

    private GroupsRepository groupsRepository;

    private UserGroupsRepository userGroupsRepository;

    private UserGroupsService userGroupsService;

    private GroupsService groupsService;

    @Autowired
    public TransactionService(GroupsService groupsService,UserGroupsService userGroupsService,TransactionRepository transactionRepository,UserRoomsRepository userRoomsRepository,RoomsRepository roomsRepository, BudgetService budgetService,GroupsRepository groupsRepository,UserGroupsRepository userGroupsRepository){
        this.transactionRepository = transactionRepository;
        this.userRoomsRepository = userRoomsRepository;
        this.roomsRepository = roomsRepository;
        this.budgetService = budgetService;
        this.groupsRepository = groupsRepository;
        this.userGroupsRepository = userGroupsRepository;
        this.userGroupsService = userGroupsService;
        this.groupsService = groupsService;
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
    public List<RoomTransactionModel> getTransactionsByRoomId(Long roomId,Long userId) throws Exception {
        if(isUserBelongsToRoom(roomId,userId)){
           List<RoomTransactionModel> transactionEntitiesResult = transactionRepository.findByRoomId(roomId);
           return transactionEntitiesResult;
        }
        else{
            throw new Exception("user doesnot belong to this room");
        }
    }

    @Transactional
    public List<RoomTransactionModel> getTransactionsByRoomIdWithUsername(long roomId) {
    	return transactionRepository.findByRoomId(roomId);
    }


    public List<RoomTransactionModel> getRoomTransactionsByCatgeory(long roomId, String catgeory) {
    	List<RoomTransactionModel> roomTransactions = transactionRepository.getRoomTransactionsByCategory(roomId, catgeory);
    	return roomTransactions;
    }

    public List<RoomTransactionModel> getRoomTransactionsByUserName(Long roomId,String userName) {
        List<RoomTransactionModel> roomTransactionsOfUser = transactionRepository.findRoomTransactionsByUserName(userName,roomId);
        return roomTransactionsOfUser;
    }

    public TransactionEntity addTransactionInGroup(TransactionEntity newTransaction) throws Exception {
        if(groupsService.isGroupOwner(newTransaction.getUserId(),newTransaction.getGroupId())){
            throw new Exception("owner cannot make transactions in group");
        }
        if(!userGroupsService.userBelongsToGroup(newTransaction.getUserId(),newTransaction.getGroupId())){
            throw new Exception("cannot add transaction under this group");
        }
        newTransaction.setCreatedOn(LocalDateTime.now());
         TransactionEntity savedTransaction =  transactionRepository.save(newTransaction);
        return savedTransaction;
    }

    public List<GroupTransactionModel> getTransactionsByGroupId(Long groupId){
        List<GroupTransactionModel> transactions = groupsRepository.getTransactionsByGroupId(groupId);
        return transactions;
    }

}
