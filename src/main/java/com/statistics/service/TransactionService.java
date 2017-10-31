package com.statistics.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.statistics.model.Transaction;
import com.statistics.singleton.TransactionsStorage;

@Service
public class TransactionService {

    public static final Long TIME_LIMIT = 60000L;

    @Autowired
    private StatisticsService statisticsService;

    public void create(Long currentTimeMillis, Transaction transaction) {
    	Double hash = ((currentTimeMillis.doubleValue() - transaction.getTimestamp().doubleValue()) / TransactionService.TIME_LIMIT.doubleValue());
    	TransactionsStorage.getInstance().addTransaction(transaction);
        statisticsService.addTransaction(hash, transaction);
    }

}