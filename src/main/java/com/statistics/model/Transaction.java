package com.statistics.model;

import static java.lang.System.currentTimeMillis;
import static com.statistics.service.TransactionService.TIME_LIMIT;

public class Transaction {

	private Double amount;
	private Long timestamp;

	public boolean isNewerThanTimeLimit() {
		System.out.println(currentTimeMillis() - timestamp);
		return currentTimeMillis() - timestamp <= TIME_LIMIT;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

}
