package com.club.fitness.customer.controller.dto.output;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Информация о членстве в спортзале")
public record MembershipOutputDto(
		@Schema(description = "Уникальный идентификатор")
		Long membershipId,
		@Schema(description = "Тип")
		String membershipType,
		@Schema(description = "Дата начала действия")
		LocalDate startDate,
		@Schema(description = "Дата окончания действия")
		LocalDate endDate) {}
