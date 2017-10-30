package com.statistics.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.statistics.model.Transaction;
import com.statistics.singleton.TransactionsStorage;

@Service
public class TransactionService {

    public static final int TIME_LIMIT = 60000;

    @Autowired
    private StatisticsService statisticsService;

    public void create(Transaction transaction) {
    	TransactionsStorage.getInstance().addTransaction(transaction);
        statisticsService.addTransaction(transaction);
    }

}