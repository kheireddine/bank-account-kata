package kata.id.bankaccount;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Transaction {

	private BigDecimal amount;
	private TransactionType transactionType;
	private LocalDate date;

}
