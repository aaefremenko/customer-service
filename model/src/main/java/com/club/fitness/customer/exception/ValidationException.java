package com.club.fitness.customer.exception;

import java.text.MessageFormat;

public class ValidationException extends RuntimeException {

	public ValidationException(final String templateMessage, final Object... params) {
		super(MessageFormat.format(templateMessage, params));
	}
}
