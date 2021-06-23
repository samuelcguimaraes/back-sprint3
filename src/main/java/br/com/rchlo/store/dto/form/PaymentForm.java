package br.com.rchlo.store.dto.form;

import br.com.rchlo.store.domain.Card;
import br.com.rchlo.store.domain.Payment;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.YearMonth;

public class PaymentForm {
	
	@NotNull
	@DecimalMin("0.01")
	private BigDecimal value;
	
	//@NotNull
	@NotBlank
	@Size(min = 1, max = 100)
	private String cardClientName;
	
	//@NotNull
	//@NotBlank
	//@Size(min = 16, max = 16)
	@Pattern(regexp = "\\d{16}", message = "deve possuir 16 dígitos")
	private String cardNumber;
	
	@NotNull
	@Future
	private YearMonth cardExpiration;
	
	//@NotNull
	//@NotBlank
	//@Size(min = 3, max = 3)
	@Pattern(regexp = "\\d{3}", message = "deve possuir 3 dígitos")
	private String cardVerificationCode;
	
	public BigDecimal getValue() {
		return this.value;
	}
	
	public String getCardClientName() {
		return this.cardClientName;
	}
	
	public String getCardNumber() {
		return this.cardNumber;
	}
	
	public YearMonth getCardExpiration() {
		return this.cardExpiration;
	}
	
	public String getCardVerificationCode() {
		return this.cardVerificationCode;
	}
	
	public Payment convert() {
		return new Payment(this.value, new Card(this.cardClientName, this.cardNumber, this.cardExpiration.toString(),
		                                        this.cardVerificationCode));
	}
}
