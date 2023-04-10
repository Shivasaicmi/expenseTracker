package com.expenseTracker.backend.controllers;

import com.expenseTracker.backend.entities.TransactionEntity;
import com.expenseTracker.backend.models.ErrorResponse;
import com.expenseTracker.backend.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService){
        this.transactionService = transactionService;
    }


    @PostMapping("/")
    public ResponseEntity<?> addTransaction(@RequestBody TransactionEntity newTransaction){
        try{
            TransactionEntity transaction = transactionService.addTransaction(newTransaction);
            return new ResponseEntity<>(transaction, HttpStatus.OK);
        }
        catch (Exception exc){
            ErrorResponse error = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,exc.getMessage());
            return new ResponseEntity<>(error,HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("/{transactionId}")
    public ResponseEntity<?> deleteTransaction(@PathVariable Long transactionId){
        try{
            transactionService.deleteTransactionById(transactionId);
            return new ResponseEntity<>("transaction with transactionId "+transactionId,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch (Exception exc){
            ErrorResponse error = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,exc.getMessage());
            return new ResponseEntity<>(error,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
