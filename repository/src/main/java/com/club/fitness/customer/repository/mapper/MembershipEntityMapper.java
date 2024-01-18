package com.club.fitness.customer.repository.mapper;

import org.springframework.stereotype.Component;

import com.club.fitness.customer.model.Membership;
import com.club.fitness.customer.model.MembershipType;
import com.club.fitness.customer.repository.entity.MembershipEntity;

@Component
public class MembershipEntityMapper {

	public Membership fromEntity(final MembershipEntity entity) {
		return new Membership(entity.getId(),
							  MembershipType.valueOf(entity.getType()),
							  entity.getStartDate(),
							  entity.getEndDate());
	}
	
	public MembershipEntity fromModel(final Membership membership) {
		return new MembershipEntity(membership.getMembershipId(),
									membership.getMembershipType().toString(),
									membership.getStartDate(),
									membership.getEndDate());
	}
}
