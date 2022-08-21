package bank_account_kata.service;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import bank.account_kata.enums.TransactionType;
import bank.account_kata.model.Account;
import bank.account_kata.service.BankService;



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
}
