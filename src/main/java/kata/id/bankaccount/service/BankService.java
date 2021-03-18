package kata.id.bankaccount.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import kata.id.bankaccount.enums.TransactionType;
import kata.id.bankaccount.exception.InvalidAmountException;
import kata.id.bankaccount.model.Account;
import kata.id.bankaccount.model.Transaction;

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

	public void transfer(Account payer, Account payee, BigDecimal amount) {

		checkTransactionAmount(amount);

		payee.addTransaction(Transaction.builder().amount(amount).date(LocalDate.now())
				.transactionType(TransactionType.DEPOSIT).build());

		payer.addTransaction(Transaction.builder().amount(amount).date(LocalDate.now())
				.transactionType(TransactionType.WITHDRAW).build());
	}

	public List<Transaction> transactionHistory(Account account) {

		return account.getTransactions();
	}

	private void checkTransactionAmount(BigDecimal amount) {

		if (amount.longValue() <= 0) {
			throw new InvalidAmountException(String.format(INVALID_AMOUNT, amount));
		}

	}

}
