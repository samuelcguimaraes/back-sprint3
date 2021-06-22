package br.com.rchlo.store.domain;

import javax.persistence.Embeddable;

@Embeddable
public class Card {
	
	private String clientName;
	private String number;
	private String expiration;
	private String verificationCode;
	
	public Card() {
	}
	
	public Card(final String clientName, final String number, final String expiration, final String verificationCode) {
		this.clientName = clientName;
		this.number = number;
		this.expiration = expiration;
		this.verificationCode = verificationCode;
	}
	
	public String getClientName() {
		return this.clientName;
	}
	
	public String getNumber() {
		return this.number;
	}
	
	public String getExpiration() {
		return this.expiration;
	}
	
	public String getVerificationCode() {
		return this.verificationCode;
	}
}
