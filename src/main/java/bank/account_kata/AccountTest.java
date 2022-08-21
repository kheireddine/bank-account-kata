package bank.account_kata;

import java.math.BigDecimal;

import org.junit.Test;

import junit.framework.Assert;
public class AccountTest {

	@Test
	public void should_deposit_in_the_account() {

		// GIVEN
		BigDecimal amount = BigDecimal.valueOf(100);

		Account account = new Account();

		// WHEN
		account.deposit(amount);

		// THEN
		Assert.assertEquals(account.getBalance(), amount);
	}

	@Test
	public void should_withdraw_from_the_account() {

		// GIVEN
		BigDecimal amountToWithdraw = BigDecimal.valueOf(50);
		Account account = new Account();

		// WHEN
		account.withdraw(amountToWithdraw);

		// THEN
		Assert.assertEquals(account.getBalance(), amountToWithdraw);
	}


}
