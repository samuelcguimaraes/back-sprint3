package br.com.rchlo.store.dto;

import br.com.rchlo.store.domain.Payment;
import br.com.rchlo.store.domain.PaymentStatus;

import java.math.BigDecimal;

public class PaymentDto {
	
	private final Long id;
	
	private final BigDecimal value;
	
	private PaymentStatus status;
	
	public PaymentDto(final Payment payment) {
		this.id = payment.getId();
		this.value = payment.getValue();
		this.status = payment.getStatus();
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
}
