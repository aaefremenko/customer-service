package com.club.fitness.customer.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.club.fitness.customer.exception.ValidationException;

public class CustomerIdTest {
	
	@ParameterizedTest
	@MethodSource("createValidCustomerIdData")
	void givenCustomerIdValue_whenCreateCustomerId_thenValidCustomerIdCreated(final Long value, final Long expectedValue) {
		final var customerId = new CustomerId(value);
		
		assertThat(customerId.getValue()).isEqualTo(expectedValue);
	}
	
	static Stream<Arguments> createValidCustomerIdData() {
		return Stream.of(Arguments.of(1L, 1L),
				Arguments.of(Long.MAX_VALUE, Long.MAX_VALUE));
	}
	
	@ParameterizedTest
	@MethodSource("createInvalidCustomerIdData")
	void givenInvalidCustomerIdValue_whenCreateCustomerId_thenThrowValidationException(final Long value,
																				   	   final String expectedExceptionMessage) {
		final var exception = assertThrows(ValidationException.class, () -> new CustomerId(value));
		
		assertThat(exception.getMessage()).isEqualTo(expectedExceptionMessage);
	}
	
	static Stream<Arguments> createInvalidCustomerIdData() {
		return Stream.of(Arguments.of(0L, "Id=0 is less than 1"),
				Arguments.of(-1L, "Id=-1 is less than 1"),
				Arguments.of(null, "Id is null"));
	}
}
