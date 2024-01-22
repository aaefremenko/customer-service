package com.club.fitness.customer.controller.dto.output;

import java.time.LocalDate;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Информация о клиенте")
public record CustomerOutputDto(
		@Schema(description = "Уникальный идентификатор")
		Long customerId,
		@Schema(description = "Уникальное имя пользователя")
		String username,
		@Schema(description = "Имя")
		String firstName,
		@Schema(description = "Фамилия")
		String lastName,
		@Schema(description = "Дата регистрации")
		LocalDate registrationDate,
		@Schema(description = "Дата и время последнего обновления")
		LocalDateTime lastModifiedDate,
		@Schema(description = "Статус")
		String status,
		@Schema(description = "Адрес")
		String address,
		@Schema(description = "Номер телефона")
		String phoneNumber,
		@Schema(description = "Электронная почта")
		String email,
		@Schema(description = "Членство в спортзале")
		MembershipOutputDto membershipOutputDto) {

}
