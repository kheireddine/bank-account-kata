package kata.id.bankaccount.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import kata.id.bankaccount.enums.TransactionType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Transaction {

	private BigDecimal amount;
	private TransactionType transactionType;
	private LocalDate date;

}
