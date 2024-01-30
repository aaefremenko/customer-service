package com.club.fitness.customer.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.club.fitness.customer.exception.ValidationException;

public class LastNameTest {
	
	@ParameterizedTest
	@MethodSource("createValidLastNameData")
	void givenLastNameValue_whenCreateLastName_thenValidLastNameCreated(final String value, final String expectedValue) {
		final var lastName = new LastName(value);
		
		assertThat(lastName.getValue()).isEqualTo(expectedValue);
	}
	
	static Stream<Arguments> createValidLastNameData() {
		return Stream.of(Arguments.of("Ivan", "Ivan"),
				Arguments.of("ivan", "ivan"),
				Arguments.of("iv-an", "iv-an"),
				Arguments.of("yu", "yu"),
				Arguments.of("u-", "u-"),
				Arguments.of("qwertyyyyyyyyyyyyyyyyyyyyyyyyyyyqwertyyyyyyyyyyyyyyyyyyyyyyyyyyy", "qwertyyyyyyyyyyyyyyyyyyyyyyyyyyyqwertyyyyyyyyyyyyyyyyyyyyyyyyyyy"));
	}
	
	@ParameterizedTest
	@MethodSource("createInvalidLastNameData")
	void givenInvalidLastNameValue_whenCreateLastName_thenThrowValidationException(final String value,
																				   final String expectedExceptionMessage) {
		final var exception = assertThrows(ValidationException.class, () -> new LastName(value));
		
		assertThat(exception.getMessage()).isEqualTo(expectedExceptionMessage);
	}
	
	static Stream<Arguments> createInvalidLastNameData() {
		return Stream.of(Arguments.of("", "last name value is blank"),
				Arguments.of("  ", "last name value is blank"),
				Arguments.of(null, "last name value is null"),
				Arguments.of("1qwerty", "last name value '1qwerty' is not valid"),
				Arguments.of("-qwerty", "last name value '-qwerty' is not valid"),
				Arguments.of("qwert_y", "last name value 'qwert_y' is not valid"),
				Arguments.of("qwerty1", "last name value 'qwerty1' is not valid"),
				Arguments.of("qwert.y", "last name value 'qwert.y' is not valid"),
				Arguments.of("q", "last name value 'q' is not valid"),
				Arguments.of("qwertyqqqqqqqqqqqqqqqqqqqqqqqqqqqqwertyqqqqqqqqqqqqqqqqqqqqqqqqqqq",
						"last name value 'qwertyqqqqqqqqqqqqqqqqqqqqqqqqqqqqwertyqqqqqqqqqqqqqqqqqqqqqqqqqqq' is not valid"));
	}
}
