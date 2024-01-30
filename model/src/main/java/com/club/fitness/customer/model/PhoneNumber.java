package com.club.fitness.customer.model;

import java.util.regex.Pattern;

import com.club.fitness.customer.exception.ValidationException;

public class PhoneNumber {
	
	
	private static final Pattern PATTERN = Pattern.compile("^\\+\\d{9,15}");
	
	private final String value;
	
	public PhoneNumber(final String value) {
		this.value = value;
		
		validate();
	}
	
	private void validate() {
		if (value == null) {
			throw new ValidationException("phone number value is null");
		}
		if (value.isBlank()) {
			throw new ValidationException("phone number value is blank");
		}
		if (!PATTERN.matcher(value).matches()) {
			throw new ValidationException("phone number value ''{0}'' is not valid", value);
		}
	}
	
	public String getValue() {
		return value;
	}
}
