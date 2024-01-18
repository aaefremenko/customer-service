package com.club.fitness.customer.controller.dto.input;

public record CustomerInputDto(String username, String firstName, String lastName, String address,
		String phoneNumber, String email, MembershipInputDto membershipInputDto) {}
