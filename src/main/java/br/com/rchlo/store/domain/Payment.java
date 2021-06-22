package br.com.rchlo.store.domain;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "payment")
public class Payment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private BigDecimal value;
	
	@Enumerated(EnumType.STRING)
	private PaymentStatus status = PaymentStatus.CREATED;
	
	@Embedded
	@AttributeOverride(name = "clientName", column = @Column(name = "card_client_name"))
	@AttributeOverride(name = "number", column = @Column(name = "card_number"))
	@AttributeOverride(name = "expiration", column = @Column(name = "card_expiration"))
	@AttributeOverride(name = "verificationCode", column = @Column(name = "card_verification_code"))
	private Card card;
	
	public Payment() {
	}
	
	public Payment(final BigDecimal value, final Card card) {
		this.value = value;
		this.card = card;
	}
	
	public Long getId() {
		return this.id;
	}
	
	public BigDecimal getValue() {
		return this.value;
	}
	
	public PaymentStatus getStatus() {
		return this.status;
	}
	
	public void confirm() {
		this.status = PaymentStatus.CONFIRMED;
	}
	
	public void cancel() {
		this.status = PaymentStatus.CANCELED;
	}
}
