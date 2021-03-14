package kata.id.bankaccount;

import java.math.BigDecimal;
import java.time.LocalDate;

import kata.id.bankaccount.exception.InvalidAmountException;

public class BankService {

	public void deposit(Account account, BigDecimal amount) {
		if (amount.longValue() <= 0) {
			throw new InvalidAmountException("Invalid Amount");
		}
		account.addTransaction(Transaction.builder().amount(amount).date(LocalDate.now())
				.transactionType(TransactionType.DEPOSIT).build());

	}

	public void withdraw(Account account, BigDecimal amount) {
		if (amount.longValue() <= 0) {
			throw new InvalidAmountException("Invalid Amount");
		}
		account.addTransaction(Transaction.builder().amount(amount).date(LocalDate.now())
				.transactionType(TransactionType.WITHDRAW).build());

	}

	public void transfer(Account payer, Account payee, BigDecimal amount) {
		if (amount.longValue() <= 0) {
			throw new InvalidAmountException("Invalid Amount");
		}
		payee.addTransaction(Transaction.builder().amount(amount).date(LocalDate.now())
				.transactionType(TransactionType.DEPOSIT).build());

		payer.addTransaction(Transaction.builder().amount(amount).date(LocalDate.now())
				.transactionType(TransactionType.WITHDRAW).build());
	}

}
