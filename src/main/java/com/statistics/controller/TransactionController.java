package com.statistics.controller;


import static com.statistics.service.TransactionService.TIME_LIMIT;
import static java.lang.System.currentTimeMillis;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.statistics.model.Transaction;
import com.statistics.service.TransactionService;

@Controller
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @RequestMapping(value = "/transactions", method = POST)
    public ResponseEntity<Void> create(@NotNull @RequestBody Transaction transaction) {
    	Long currentTimeMillis = currentTimeMillis();
        if (currentTimeMillis - transaction.getTimestamp() > TIME_LIMIT) {
            return new ResponseEntity<>(NO_CONTENT);
        } else {
            transactionService.create(currentTimeMillis, transaction);
            return new ResponseEntity<>(CREATED);
        }
    }

}