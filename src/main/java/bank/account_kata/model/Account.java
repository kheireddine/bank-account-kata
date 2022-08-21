package bank.account_kata.model;

import java.util.ArrayList;
import java.util.List;


import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Account {
	private List<Transaction> transactions = new ArrayList<>();

	public void addTransaction(Transaction transaction) {
		this.transactions.add(transaction);
	}

}


