package bank_account_kata.service;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import bank.account_kata.enums.TransactionType;
import bank.account_kata.model.Account;
import bank.account_kata.service.BankService;
import bank.account_kata.model.Transaction;
import bank.account_kata.exception.InvalidAmountException;

public class BankServiceTest {
	private BankService bankService = new BankService();

	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test
	public void should_deposit_in_the_account() {

		// GIVEN
		Account account = new Account();
		LocalDate now = LocalDate.now();
		String formatDate = now.format(formatter);

		// WHEN
		bankService.deposit(account, BigDecimal.valueOf(100));

		// THEN
		assertEquals(BigDecimal.valueOf(100), account.getTransactions().get(0).getAmount());
		assertEquals(TransactionType.DEPOSIT, account.getTransactions().get(0).getTransactionType());
		assertEquals(formatDate, account.getTransactions().get(0).getDate().format(formatter));
	}

	@Test
	public void should_withdraw_from_the_account() {

		// GIVEN
		Account account = new Account();
		LocalDate now = LocalDate.now();
		String formatDate = now.format(formatter);

		// WHEN
		bankService.withdraw(account, BigDecimal.valueOf(50));

		// THEN
		assertEquals(BigDecimal.valueOf(50), account.getTransactions().get(0).getAmount());
		assertEquals(TransactionType.WITHDRAW, account.getTransactions().get(0).getTransactionType());
		assertEquals(formatDate, account.getTransactions().get(0).getDate().format(formatter));
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
	public void should_return_transaction_history_for_account() {

		// GIVEN
		Account account = new Account();

		bankService.deposit(account, BigDecimal.valueOf(100));
		bankService.deposit(account, BigDecimal.valueOf(200));
		bankService.deposit(account, BigDecimal.valueOf(300));

		bankService.withdraw(account, BigDecimal.valueOf(50));
		bankService.withdraw(account, BigDecimal.valueOf(150));

		// WHEN
		List<Transaction> transactionList = bankService.transactionHistory(account);

		// THEN
		assertEquals(5, transactionList.size());
		assertEquals(BigDecimal.valueOf(100), transactionList.get(0).getAmount());
		assertEquals(TransactionType.DEPOSIT, transactionList.get(0).getTransactionType());

		assertEquals(3, transactionList.stream().filter(tr -> tr.getTransactionType().equals(TransactionType.DEPOSIT))
				.collect(Collectors.toList()).size());

		assertEquals(2, transactionList.stream().filter(tr -> tr.getTransactionType().equals(TransactionType.WITHDRAW))
				.collect(Collectors.toList()).size());

	}

	@Test
	public void should_throw_InvalidAmountException_with_invalid_amount_message() {

		// GIVEN
		Account account = new Account();
		exception.expect(InvalidAmountException.class);
		exception.expectMessage("Invalid Amount : -2");

		// WHEN
		bankService.deposit(account, BigDecimal.valueOf(-2));

	}

	@Test
	public void should_return_balance_for_acount() {
		// GIVEN
		Account account = new Account();

		bankService.deposit(account, BigDecimal.valueOf(250));
		bankService.deposit(account, BigDecimal.valueOf(300));
		bankService.deposit(account, BigDecimal.valueOf(550));

		bankService.withdraw(account, BigDecimal.valueOf(200));
		bankService.withdraw(account, BigDecimal.valueOf(170));
		bankService.withdraw(account, BigDecimal.valueOf(230));

		// WHEN
		BigDecimal balance = bankService.getBalance(account);

		// THEN
		assertEquals(BigDecimal.valueOf(500), balance);

	}

	@Test
	public void should_return_zero_when_no_transaction_found() {
		// GIVEN
		Account account = new Account();

		// WHEN
		BigDecimal balance = bankService.getBalance(account);

		// THEN
		assertEquals(BigDecimal.valueOf(0), balance);

	}
}
