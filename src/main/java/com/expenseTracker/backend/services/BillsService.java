package com.expenseTracker.backend.services;

import com.expenseTracker.backend.entities.BillsEntity;
import com.expenseTracker.backend.repositories.BillsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillsService {

    private BillsRepository billsRepository;

    @Autowired
    public BillsService(BillsRepository billsRepository){
        this.billsRepository = billsRepository;
    }

    public BillsEntity addBill(BillsEntity newbill){
       BillsEntity savedBill = billsRepository.save(newbill);
       return savedBill;
    }

    public List<BillsEntity> getBills(long userId){
        List<BillsEntity> bills = billsRepository.findByUserId(userId);
        return bills;
    }

    public void deleteBill(long billId){
        billsRepository.deleteById(billId);
    }

}
