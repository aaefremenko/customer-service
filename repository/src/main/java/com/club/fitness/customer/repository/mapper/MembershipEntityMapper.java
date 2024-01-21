package com.club.fitness.customer.repository.mapper;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.club.fitness.customer.model.Membership;
import com.club.fitness.customer.model.MembershipId;
import com.club.fitness.customer.model.MembershipType;
import com.club.fitness.customer.repository.entity.MembershipEntity;

@Component
public class MembershipEntityMapper {

	public Membership fromEntity(final MembershipEntity entity) {
		return new Membership(Optional.ofNullable(entity.getId())
									  .map(MembershipId::new)
									  .orElse(null),
							  MembershipType.valueOf(entity.getType()),
							  entity.getStartDate(),
							  entity.getEndDate());
	}
	
	public MembershipEntity fromModel(final Membership membership) {
		return new MembershipEntity(Optional.ofNullable(membership.getMembershipId())
											.map(MembershipId::getValue)
											.orElse(null),
									membership.getMembershipType().toString(),
									membership.getStartDate(),
									membership.getEndDate());
	}
}
