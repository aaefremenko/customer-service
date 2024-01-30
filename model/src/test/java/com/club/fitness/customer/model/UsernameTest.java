package com.club.fitness.customer.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.club.fitness.customer.exception.ValidationException;

public class UsernameTest {
	
	@ParameterizedTest
	@MethodSource("createValidUsernameData")
	void givenUsernameValue_whenCreateUsername_thenValidUsernameCreated(final String value, final String expectedValue) {
		final var username = new Username(value);
		
		assertThat(username.getValue()).isEqualTo(expectedValue);
	}
	
	static Stream<Arguments> createValidUsernameData() {
		return Stream.of(Arguments.of("qwerty", "qwerty"),
				Arguments.of("qwe_rty", "qwe_rty"),
				Arguments.of("qwerty_", "qwerty_"),
				Arguments.of("qwerty1", "qwerty1"),
				Arguments.of("qwertyyyyyyyyyyyyyyyyyyyyyyyyyyy", "qwertyyyyyyyyyyyyyyyyyyyyyyyyyyy"));
	}
	
	@ParameterizedTest
	@MethodSource("createInvalidUsernameData")
	void givenInvalidUsernameValue_whenCreateUsername_thenThrowValidationException(final String value,
																				   final String expectedExceptionMessage) {
		final var exception = assertThrows(ValidationException.class, () -> new Username(value));
		
		assertThat(exception.getMessage()).isEqualTo(expectedExceptionMessage);
	}
	
	static Stream<Arguments> createInvalidUsernameData() {
		return Stream.of(Arguments.of("", "username value is blank"),
				Arguments.of("  ", "username value is blank"),
				Arguments.of(null, "username value is null"),
				Arguments.of("1qwerty", "username value '1qwerty' is not valid"),
				Arguments.of("_qwerty", "username value '_qwerty' is not valid"),
				Arguments.of("qwert-y", "username value 'qwert-y' is not valid"),
				Arguments.of("qwert.y", "username value 'qwert.y' is not valid"),
				Arguments.of("qwert", "username value 'qwert' is not valid"),
				Arguments.of("qwertyqqqqqqqqqqqqqqqqqqqqqqqqqqq", "username value 'qwertyqqqqqqqqqqqqqqqqqqqqqqqqqqq' is not valid"));
	}
}
