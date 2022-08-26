package bank.account_kata.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import bank.account_kata.model.Account;
import bank.account_kata.enums.TransactionType;
import bank.account_kata.exception.InvalidAmountException;
import bank.account_kata.model.Transaction;



public class BankService {
	private static final String INVALID_AMOUNT = "Invalid Amount : %s";

	public void deposit(Account account, BigDecimal amount) {

		checkTransactionAmount(amount);

		account.addTransaction(Transaction.builder().amount(amount).date(LocalDate.now())
				.transactionType(TransactionType.DEPOSIT).build());

	}

	public void withdraw(Account account, BigDecimal amount) {

		checkTransactionAmount(amount);

		account.addTransaction(Transaction.builder().amount(amount).date(LocalDate.now())
				.transactionType(TransactionType.WITHDRAW).build());

	}
	
	private void checkTransactionAmount(BigDecimal amount) {

		if (amount.longValue() <= 0) {
			throw new InvalidAmountException(String.format(INVALID_AMOUNT, amount));
		}

	}
	public List<Transaction> transactionHistory(Account account) {

		return account.getTransactions();
	}
	
	public BigDecimal getBalance(Account account) {

		BigDecimal totalDeposit = account.getTransactions().stream()
				.filter(trasanction -> trasanction.getTransactionType().equals(TransactionType.DEPOSIT))
				.map(Transaction::getAmount)
				.reduce(BigDecimal::add)
				.orElse(BigDecimal.ZERO);

		BigDecimal totalWithraw = account.getTransactions().stream()
				.filter(trasanction -> trasanction.getTransactionType().equals(TransactionType.WITHDRAW))
				.map(Transaction::getAmount)
				.reduce(BigDecimal::add)
				.orElse(BigDecimal.ZERO);

		return totalDeposit.subtract(totalWithraw);
	}
}
