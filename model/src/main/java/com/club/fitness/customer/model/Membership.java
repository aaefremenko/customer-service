package com.club.fitness.customer.model;

import java.time.LocalDate;

public class Membership {
	
	private final Long membershipId;
	private final MembershipType membershipType;
	private final LocalDate startDate;
	private final LocalDate endDate;
	
	public Membership(final Long membershipId, final MembershipType membershipType,
					  final LocalDate startDate, final LocalDate endDate) {
		this.membershipId = membershipId;
		this.membershipType = membershipType;
		this.startDate = startDate;
		this.endDate = endDate;
		
		validate();
	}
	
	private void validate() {
		
	}
	
	public Membership update(final Membership newMembership) {
		return new Membership(membershipId,
							  newMembership.membershipType,
							  newMembership.startDate,
							  newMembership.endDate);
	}
	
	public Long getMembershipId() {
		return membershipId;
	}
	
	public MembershipType getMembershipType() {
		return membershipType;
	}
	
	public LocalDate getStartDate() {
		return startDate;
	}
	
	public LocalDate getEndDate() {
		return endDate;
	}
	
}
