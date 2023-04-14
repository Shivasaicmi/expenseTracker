package com.expenseTracker.backend.services;

import com.expenseTracker.backend.customExceptions.CategoryNotFoundException;
import com.expenseTracker.backend.entities.TransactionEntity;
import com.expenseTracker.backend.repositories.TransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    private TransactionRepository transactionRepository;
    private BudgetService budgetService;

    public TransactionService(TransactionRepository transactionRepository, BudgetService budgetService){
        this.transactionRepository = transactionRepository;
        this.budgetService = budgetService;
    }

    public TransactionEntity addTransaction(TransactionEntity newTransaction){
        newTransaction.setCreatedOn(LocalDateTime.now());
        TransactionEntity addedTransaction =  transactionRepository.save(newTransaction);
        return addedTransaction;
    }
    
    @Transactional
    public TransactionEntity addPersonalTransaction(TransactionEntity transaction) {
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
    public void addTransactionByRoomId(TransactionEntity transactionEntity,Long roomId){

    }

}
