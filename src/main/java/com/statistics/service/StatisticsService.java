package com.statistics.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.statistics.model.Statistics;
import com.statistics.model.Transaction;

@Lazy(false)
@Service
public class StatisticsService {

    private static final int POLLING_INTERVAL_RATE_MILLIS = 1;

    private Map<Double,Transaction> transactions60Seconds = new HashMap<Double,Transaction>(TransactionService.TIME_LIMIT.intValue());
    
    private Statistics statistics = new Statistics(transactions60Seconds);

    @Scheduled(fixedRate = POLLING_INTERVAL_RATE_MILLIS)
    private void evictOldEntries() {
        transactions60Seconds.entrySet().removeIf(entry -> !entry.getValue().isNewerThanTimeLimit());
        updateStatistics();
    }

    public void addTransaction(Double hash, Transaction transaction) {
    	transactions60Seconds.put(hash, transaction);
        updateStatistics();
    }

    private void updateStatistics() {
        setStatistics(new Statistics(transactions60Seconds));
    }

	public Statistics getStatistics() {
		return statistics;
	}

	public void setStatistics(Statistics statistics) {
		this.statistics = statistics;
	}
    
}