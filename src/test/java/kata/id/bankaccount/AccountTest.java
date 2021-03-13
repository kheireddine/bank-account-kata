package kata.id.bankaccount;

import java.math.BigDecimal;

import org.junit.Test;

import junit.framework.Assert;
import kata.id.bankaccount.exception.InvalidAmountException;

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
		Assert.assertEquals(account.getBalance(), BigDecimal.valueOf(-50));
	}

	
	@Test(expected = InvalidAmountException.class)
	public void given_an_amount_less_than_or_equal_0_then_the_deposit_throw_InvalidAmountException() {

		// GIVEN
		BigDecimal amount = BigDecimal.valueOf(-2);
		Account account = new Account();

		// WHEN
		account.deposit(amount);
	}
	
	@Test(expected = InvalidAmountException.class)
	public void given_an_amount_less_than_or_equal_0_then_the_withdraw_throw_InvalidAmountException() {

		// GIVEN
		BigDecimal amount = BigDecimal.valueOf(-2);
		Account account = new Account();

		// WHEN
		account.withdraw(amount);
	}
}
