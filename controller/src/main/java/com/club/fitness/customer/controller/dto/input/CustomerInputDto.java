package com.club.fitness.customer.controller.dto.input;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Информация о клиенте")
public record CustomerInputDto(
		@Schema(description = "Уникальное имя пользователя")
		String username,
		@Schema(description = "Имя")
		String firstName,
		@Schema(description = "Фамилия")
		String lastName,
		@Schema(description = "Адрес")
		String address,
		@Schema(description = "Номер телефона")
		String phoneNumber,
		@Schema(description = "Электронная почта")
		String email,
		@Schema(description = "Членство в спортзале")
		MembershipInputDto membershipInputDto) {}
