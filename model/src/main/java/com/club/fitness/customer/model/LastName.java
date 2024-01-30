package com.club.fitness.customer.model;

import java.util.regex.Pattern;

import com.club.fitness.customer.exception.ValidationException;

public class LastName {
	
	private static final Pattern PATTERN = Pattern.compile("^[A-Za-z][A-Za-z-]{1,63}$");
	
	private final String value;
	
	public LastName(final String value) {
		this.value = value;
		
		validate();
	}
	
	private void validate() {
		if (value == null) {
			throw new ValidationException("last name value is null");
		}
		if (value.isBlank()) {
			throw new ValidationException("last name value is blank");
		}
		if (!PATTERN.matcher(value).matches()) {
			throw new ValidationException("last name value ''{0}'' is not valid", value);
		}
	}
	
	public String getValue() {
		return value;
	}
}
