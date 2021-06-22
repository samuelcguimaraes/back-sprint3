package br.com.rchlo.store.config.validation;

public class FormErrorDto {
	
	private String field;
	private String message;
	
	public FormErrorDto(String field, String message) {
		this.field = field;
		this.message = message;
	}
	
	public String getField() {
		return this.field;
	}
	
	public String getMessage() {
		return this.message;
	}
	
}