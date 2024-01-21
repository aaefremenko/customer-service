package com.club.fitness.customer.model;

import com.club.fitness.customer.exception.ValidationException;

public class Address {
	
	private final String value;
	
	public Address(final String value) {
		this.value = value;
		
		validate();
	}
	
	private void validate() {
		if (value == null) {
			throw new ValidationException("address value is null");
		}
		if (value.isBlank()) {
			throw new ValidationException("address value is blank");
		}
		if (value.length() > 128) {
			throw new ValidationException("address value ''{0}'' length is more than 128", value);
		}
	}
	
	public String getValue() {
		return value;
	}
}
