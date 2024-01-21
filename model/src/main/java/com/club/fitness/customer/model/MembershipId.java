package com.club.fitness.customer.model;

import com.club.fitness.customer.exception.ValidationException;

public class MembershipId {
	
	private final Long value;

	public MembershipId(final Long value) {
		this.value = value;
		
		validate();
	}
	
	private void validate() {
		if (value == null)  {
			throw new ValidationException("Id is null");
		}
		if (value < 1L) {
			throw new ValidationException("Id={0} is less than 1", value);
		}
	}

	public Long getValue() {
		return value;
	}
}
