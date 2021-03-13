package kata.id.bankaccount;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

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
		 assertEquals(amount,account.getBalance() );
	}

	@Test
	public void should_withdraw_from_the_account() {

		// GIVEN
		BigDecimal amountToWithdraw = BigDecimal.valueOf(50);
		Account account = new Account();

		// WHEN
		account.withdraw(amountToWithdraw);

		// THEN
		 assertEquals(BigDecimal.valueOf(-50), account.getBalance());
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
	
	
	@Test
	public void should_transfer_amont_from_payer_to_another_payee() {
		//	GIVEN
		BigDecimal amount = BigDecimal.valueOf(100);
		Account payer = new Account();
		Account payee = new Account();
		
		// 	WHEN
		payer.transfer(payee,amount);
		
		// 	THEN
		 assertEquals( BigDecimal.valueOf(-100), payer.getBalance());
		 assertEquals( BigDecimal.valueOf(100), payee.getBalance());
		
		
	}
	
	
	@Test(expected = InvalidAmountException.class)
	public void given_an_amount_less_than_or_equal_0_then_the_transfer_throw_InvalidAmountException() {

		//	GIVEN
		BigDecimal amount = BigDecimal.valueOf(-22);
		Account accountA = new Account();
		Account accountB = new Account();
		
		// 	WHEN
		accountA.transfer(accountB,amount);

	 
	}
	
}
