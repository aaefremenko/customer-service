package com.club.fitness.customer.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.club.fitness.customer.exception.ValidationException;

public class PhoneNumberTest {

	@ParameterizedTest
	@MethodSource("createValidPhoneNumberData")
	void givenPhoneNumberValue_whenCreatePhoneNumber_thenValidPhoneNumberCreated(final String value, final String expectedValue) {
		final var phoneNumber = new PhoneNumber(value);
		
		assertThat(phoneNumber.getValue()).isEqualTo(expectedValue);
	}
	
	static Stream<Arguments> createValidPhoneNumberData() {
		return Stream.of(Arguments.of("+123456789", "+123456789"),
				Arguments.of("+123456789012345", "+123456789012345"));
	}
	
	@ParameterizedTest
	@MethodSource("createInvalidPhoneNumberData")
	void givenInvalidPhoneNumberValue_whenCreatePhoneNumber_thenThrowValidationException(final String value,
			final String expectedExceptionMessage) {
		final var exception = assertThrows(ValidationException.class, () -> new PhoneNumber(value));
		
		assertThat(exception.getMessage()).isEqualTo(expectedExceptionMessage);
	}
	
	static Stream<Arguments> createInvalidPhoneNumberData() {
		return Stream.of(Arguments.of("", "phone number value is blank"),
				Arguments.of("  ", "phone number value is blank"),
				Arguments.of(null, "phone number value is null"),
				Arguments.of("123456789", "phone number value '123456789' is not valid"),
				Arguments.of("+12-3456789", "phone number value '+12-3456789' is not valid"),
				Arguments.of("+12345678", "phone number value '+12345678' is not valid"),
				Arguments.of("+1234567890123456", "phone number value '+1234567890123456' is not valid"));
	}
}
