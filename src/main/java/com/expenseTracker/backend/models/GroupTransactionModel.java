package com.expenseTracker.backend.models;

import org.springframework.beans.factory.annotation.Value;

public interface GroupTransactionModel {

    @Value("#{target.username}")
    String getUserName();

    @Value("#{target.paid}")
    Double getPaid();

    @Value("#{target.has_to_pay}")
    Long getHasToPay();

}
