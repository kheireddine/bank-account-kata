package bank.account_kata.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import bank.account_kata.enums.TransactionType;
import lombok.Builder;
import lombok.Getter;
 

@Getter
@Builder
public class Transaction {

	private BigDecimal amount;
	private TransactionType transactionType;
	private LocalDate date;

}
