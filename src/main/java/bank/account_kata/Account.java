package bank.account_kata;

import java.math.BigDecimal;

public class Account {

	private BigDecimal balance = BigDecimal.ZERO;
	public BigDecimal getBalance() {
		return balance;
	}
	
	public Account() {

	}

	public void deposit(BigDecimal amount) {
		if (amount.longValue() <= 0) {
			throw new NumberFormatException("Amount invalid");
		}
		this.balance = this.balance.add(amount);

	}

	public void withdraw(BigDecimal amount) {
		if (amount.longValue() <= 0) {
			throw new NumberFormatException("Amount invalid");
		}

		this.balance = this.balance.subtract(amount);
		System.out.println(this.balance);
	}
}
	

