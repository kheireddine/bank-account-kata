package bank.account_kata;

import java.math.BigDecimal;

public class Account {

	private BigDecimal balance = BigDecimal.ZERO;

	public Account() {

	}

	public void deposit(BigDecimal amount) {
		if (amount.longValue() <= 0) {
			throw new NumberFormatException("Amount invalid");
		}
		this.balance = this.balance.add(amount);

	}


	public BigDecimal getBalance() {
		return balance;
	}
}
