package com.expenseTracker.backend.services;

import com.expenseTracker.backend.entities.TransactionEntity;
import com.expenseTracker.backend.repositories.TransactionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TransactionService {

    private TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository){
        this.transactionRepository = transactionRepository;
    }

    // add a transaction

    public TransactionEntity addTransaction(TransactionEntity newTransaction){
        newTransaction.setCreatedOn(LocalDateTime.now());
        TransactionEntity addedTransaction =  transactionRepository.save(newTransaction);
        return addedTransaction;
    }

    // delete a transaction

    // update a transaction

    // get transaction by UserId

    // get transaction by transaction id

}
