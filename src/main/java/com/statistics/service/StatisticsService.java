package com.statistics.service;

import java.util.concurrent.PriorityBlockingQueue;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.statistics.comparator.TransactionTimestampComparator;
import com.statistics.model.Statistics;
import com.statistics.model.Transaction;

@Service
public class StatisticsService {

    private static final int QUEUE_INITIAL_CAPACITY = 1000;
    private static final int POLLING_INTERVAL_RATE_MILLIS = 1;

    private final PriorityBlockingQueue<Transaction> transactionsLast60Seconds =
            new PriorityBlockingQueue<>(QUEUE_INITIAL_CAPACITY, new TransactionTimestampComparator());

    private Statistics statistics = new Statistics(transactionsLast60Seconds);

    @Scheduled(fixedRate = POLLING_INTERVAL_RATE_MILLIS)
    private void evictOldEntries() {
        while (!transactionsLast60Seconds.isEmpty() && !transactionsLast60Seconds.peek().isNewerThanTimeLimit()) {
            transactionsLast60Seconds.poll();
        }
        updateStatistics();
    }

    public Statistics getStatistics() {
		return statistics;
	}

	public void setStatistics(Statistics statistics) {
		this.statistics = statistics;
	}

	public void addTransaction(Transaction transaction) {
        transactionsLast60Seconds.add(transaction);
        updateStatistics();
    }

    private void updateStatistics() {
        statistics = new Statistics(transactionsLast60Seconds);
    }
    
}