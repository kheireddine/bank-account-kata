package kata.id.bankaccount;

import java.math.BigDecimal;

import kata.id.bankaccount.exception.InvalidAmountException;

public class Account {

	private BigDecimal balance = BigDecimal.ZERO;

	public Account() {

	}

	public void deposit(BigDecimal amount) {
		if (amount.longValue() <= 0) {
			throw new InvalidAmountException("Invalid Amount");
		}
		this.balance = this.balance.add(amount);

	}

	public void withdraw(BigDecimal amount) {
		if (amount.longValue() <= 0) {
			throw new InvalidAmountException("Invalid Amount");
		}

		this.balance = this.balance.subtract(amount);
	}

	public void transfer(Account payee, BigDecimal amount) {
		if (amount.longValue() <= 0) {
			throw new InvalidAmountException("Invalid Amount");
		}
		this.withdraw(amount);
		payee.deposit(amount);

	}

	public BigDecimal getBalance() {
		return balance;
	}

}
