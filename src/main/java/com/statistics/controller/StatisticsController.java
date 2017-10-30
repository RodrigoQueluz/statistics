package com.statistics.controller;

import static org.springframework.http.HttpStatus.OK;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.statistics.model.Statistics;
import com.statistics.service.StatisticsService;

@Controller
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;

    @RequestMapping("/statistics")
    @ResponseBody
    public ResponseEntity<Statistics> getTransactions() {
        return new ResponseEntity<>(statisticsService.getStatistics(), OK);
    }
}
