package com.expenseTracker.backend.services;

import com.expenseTracker.backend.entities.TransactionEntity;
import com.expenseTracker.backend.repositories.TransactionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    private TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository){
        this.transactionRepository = transactionRepository;
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
}
