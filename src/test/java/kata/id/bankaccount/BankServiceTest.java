package kata.id.bankaccount;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

import kata.id.bankaccount.exception.InvalidAmountException;

public class BankServiceTest {
	private BankService bankService = new BankService();

	@Test
	public void should_deposit_in_the_account() {

		// GIVEN

		Account account = new Account();

		// WHEN
		bankService.deposit(account, BigDecimal.valueOf(100));

		// THEN
		assertEquals(BigDecimal.valueOf(100), account.getTransactions().get(0).getAmount());
		assertEquals(TransactionType.DEPOSIT, account.getTransactions().get(0).getTransactionType());
	}

	@Test
	public void should_withdraw_from_the_account() {

		// GIVEN
		Account account = new Account();

		// WHEN
		bankService.withdraw(account, BigDecimal.valueOf(50));

		// THEN
		assertEquals(BigDecimal.valueOf(50), account.getTransactions().get(0).getAmount());
		assertEquals(TransactionType.WITHDRAW, account.getTransactions().get(0).getTransactionType());
	}

	@Test(expected = InvalidAmountException.class)
	public void given_an_amount_less_than_or_equal_0_then_the_deposit_throw_InvalidAmountException() {

		// GIVEN
		Account account = new Account();

		// WHEN
		bankService.deposit(account, BigDecimal.valueOf(-2));
	}

	@Test(expected = InvalidAmountException.class)
	public void given_an_amount_less_than_or_equal_0_then_the_withdraw_throw_InvalidAmountException() {

		// GIVEN
		Account account = new Account();

		// WHEN
		bankService.withdraw(account, BigDecimal.valueOf(-2));
	}

	@Test
	public void should_transfer_amont_from_payer_to_another_payee() {
		// GIVEN
		BigDecimal amount = BigDecimal.valueOf(100);
		Account payer = new Account();
		Account payee = new Account();

		// WHEN
		bankService.transfer(payer, payee, amount);

		// THEN
		assertEquals(BigDecimal.valueOf(100), payer.getTransactions().get(0).getAmount());
		assertEquals(TransactionType.WITHDRAW, payer.getTransactions().get(0).getTransactionType());
		assertEquals(BigDecimal.valueOf(100), payee.getTransactions().get(0).getAmount());
		assertEquals(TransactionType.DEPOSIT, payee.getTransactions().get(0).getTransactionType());

	}

	@Test(expected = InvalidAmountException.class)
	public void given_an_amount_less_than_or_equal_0_then_the_transfer_throw_InvalidAmountException() {

		// GIVEN
		BigDecimal amount = BigDecimal.valueOf(-22);
		Account payer = new Account();
		Account payee = new Account();

		// WHEN
		bankService.transfer(payer, payee, amount);

	}

}
