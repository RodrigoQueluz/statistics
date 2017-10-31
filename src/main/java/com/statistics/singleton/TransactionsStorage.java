package com.statistics.singleton;

import java.util.ArrayList;
import java.util.List;

import com.statistics.model.Transaction;

public class TransactionsStorage {
	private static TransactionsStorage instance = null;
	
	private List<Transaction> transactions = new ArrayList<Transaction>();

	private TransactionsStorage() {
	}

	public synchronized static TransactionsStorage getInstance() {
		if (instance == null) {
			instance = new TransactionsStorage();
		}
		return instance;
	}
	
	public void addTransaction(Transaction transaction){
		this.transactions.add(transaction);
	}
	
}
