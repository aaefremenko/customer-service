package com.club.fitness.customer.model;

import java.util.regex.Pattern;

import com.club.fitness.customer.exception.ValidationException;

public class Email {
	
	private static final Pattern PATTERN = Pattern.compile("^[A-Za-z0-9][A-Za-z0-9_.-]+@[a-z]{2,}[.][a-z]{2,}$");
	
	private final String value;
	
	public Email(final String value) {
		this.value = value;
		
		validate();
	}
	
	private void validate() {
		if (value == null) {
			throw new ValidationException("email value is null");
		}
		if (value.isBlank()) {
			throw new ValidationException("email value is blank");
		}
		if (value.length() > 32) {
			throw new ValidationException("email value ''{0}'' length is more than 32", value);
		}
		if (!PATTERN.matcher(value).matches()) {
			throw new ValidationException("email value ''{0}'' is not valid", value);
		}
	}
	
	public String getValue() {
		return value;
	}
}
