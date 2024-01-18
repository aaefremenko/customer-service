package com.club.fitness.customer.controller.dto.output;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record CustomerOutputDto(Long customerId, String username, String firstName, String lastName,
		LocalDate registrationDate, LocalDateTime lastModifiedDate, String status,
		String address, String phoneNumber, String email, MembershipOutputDto membershipOutputDto) {

}
