package com.club.fitness.customer.controller.dto.input;

import java.time.LocalDate;

public record MembershipInputDto(String membershipType, LocalDate startDate, LocalDate endDate) {}
