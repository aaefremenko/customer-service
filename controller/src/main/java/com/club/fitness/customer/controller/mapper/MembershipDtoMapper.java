package com.club.fitness.customer.controller.mapper;

import org.springframework.stereotype.Component;

import com.club.fitness.customer.controller.dto.input.MembershipInputDto;
import com.club.fitness.customer.controller.dto.output.MembershipOutputDto;
import com.club.fitness.customer.model.Membership;
import com.club.fitness.customer.model.MembershipType;

@Component
public class MembershipDtoMapper {

	public Membership fromDtoWithId(final Long id, final MembershipInputDto inputDto) {
		return new Membership(id,
							  MembershipType.valueOf(inputDto.membershipType()),
							  inputDto.startDate(),
		       				  inputDto.endDate());
	}
	
	public MembershipOutputDto fromModel(final Membership membership) {
		return new MembershipOutputDto(membership.getMembershipId(),
									   membership.getMembershipType().toString(),
									   membership.getStartDate(),
									   membership.getEndDate());
	}
}
