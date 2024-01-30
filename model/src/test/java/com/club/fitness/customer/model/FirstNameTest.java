package com.club.fitness.customer.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.club.fitness.customer.exception.ValidationException;

public class FirstNameTest {
	
	@ParameterizedTest
	@MethodSource("createValidFirstNameData")
	void givenFirstNameValue_whenCreateFirstName_thenValidFirstNameCreated(final String value, final String expectedValue) {
		final var firstName = new FirstName(value);
		
		assertThat(firstName.getValue()).isEqualTo(expectedValue);
	}
	
	static Stream<Arguments> createValidFirstNameData() {
		return Stream.of(Arguments.of("Ivan", "Ivan"),
				Arguments.of("ivan", "ivan"),
				Arguments.of("iv-an", "iv-an"),
				Arguments.of("yu", "yu"),
				Arguments.of("u-", "u-"),
				Arguments.of("qwertyyyyyyyyyyyyyyyyyyyyyyyyyyy", "qwertyyyyyyyyyyyyyyyyyyyyyyyyyyy"));
	}
	
	@ParameterizedTest
	@MethodSource("createInvalidFirstNameData")
	void givenInvalidFirstNameValue_whenCreateFirstName_thenThrowValidationException(final String value,
																				     final String expectedExceptionMessage) {
		final var exception = assertThrows(ValidationException.class, () -> new FirstName(value));
		
		assertThat(exception.getMessage()).isEqualTo(expectedExceptionMessage);
	}
	
	static Stream<Arguments> createInvalidFirstNameData() {
		return Stream.of(Arguments.of("", "first name value is blank"),
				Arguments.of("  ", "first name value is blank"),
				Arguments.of(null, "first name value is null"),
				Arguments.of("1qwerty", "first name value '1qwerty' is not valid"),
				Arguments.of("-qwerty", "first name value '-qwerty' is not valid"),
				Arguments.of("qwert_y", "first name value 'qwert_y' is not valid"),
				Arguments.of("qwerty1", "first name value 'qwerty1' is not valid"),
				Arguments.of("qwert.y", "first name value 'qwert.y' is not valid"),
				Arguments.of("q", "first name value 'q' is not valid"),
				Arguments.of("qwertyqqqqqqqqqqqqqqqqqqqqqqqqqqq", "first name value 'qwertyqqqqqqqqqqqqqqqqqqqqqqqqqqq' is not valid"));
	}
}
