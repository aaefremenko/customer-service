package com.club.fitness.customer.controller.dto.output;

import java.time.LocalDate;

public record MembershipOutputDto(Long membershipId, String membershipType, LocalDate startDate,
		LocalDate endDate) {}
