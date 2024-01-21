package com.club.fitness.customer.model;

import java.util.regex.Pattern;

import com.club.fitness.customer.exception.ValidationException;

public class Username {
	
	private static final Pattern PATTERN = Pattern.compile("^[A-Za-z]\\w{5,31}$");
	
	private final String value;
	
	public Username(final String value) {
		this.value = value;
		
		validate();
	}
	
	private void validate() {
		if (value == null) {
			throw new ValidationException("username value is null");
		}
		if (value.isBlank()) {
			throw new ValidationException("username value is blank");
		}
		if (!PATTERN.matcher(value).matches()) {
			throw new ValidationException("username value ''{0}'' is not valid", value);
		}
	}
	
	public String getValue() {
		return value;
	}
}
