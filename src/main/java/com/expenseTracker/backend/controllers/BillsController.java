package com.expenseTracker.backend.controllers;

import com.expenseTracker.backend.entities.BillsEntity;
import com.expenseTracker.backend.services.BillsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bills")
public class BillsController {

    private BillsService billsService;

    public BillsController(BillsService billsService){
        this.billsService = billsService;
    }

    @PostMapping("/")
    public ResponseEntity<?> addBills(BillsEntity newBill){
        try{
           BillsEntity savedBill = billsService.addBill(newBill);
            return new ResponseEntity<>(savedBill, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getBills(@PathVariable("userId") Long userId){
        try{
            List<BillsEntity> bills = billsService.getBills(userId);
            return new ResponseEntity<>(bills,HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBill(@PathVariable("id") long id){
        try{
            billsService.deleteBill(id);
            return new ResponseEntity<>("deleted successfully",HttpStatus.OK);
        }
        catch (Exception exc){
            return new ResponseEntity<>("error, cannot delete the bill "+id,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
