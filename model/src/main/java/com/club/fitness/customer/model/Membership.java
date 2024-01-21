package com.club.fitness.customer.model;

import java.time.LocalDate;

import com.club.fitness.customer.exception.ValidationException;

public class Membership {
	
	private final MembershipId membershipId;
	private final MembershipType membershipType;
	private final LocalDate startDate;
	private final LocalDate endDate;
	
	public Membership(final MembershipId membershipId,
					  final MembershipType membershipType,
					  final LocalDate startDate,
					  final LocalDate endDate) {
		this.membershipId = membershipId;
		this.membershipType = membershipType;
		this.startDate = startDate;
		this.endDate = endDate;
		
		validate();
	}
	
	private void validate() {
		if (membershipType == null) {
			throw new ValidationException("membershipType is null");
		}
		if (startDate == null) {
			throw new ValidationException("startDate is null");
		}
		if (endDate == null) {
			throw new ValidationException("endDate is null");
		}
		if (startDate.isAfter(endDate)) {
			throw new ValidationException("startDate={0} is after endDate={1}", startDate.toString(), endDate.toString());
		}
	}
	
	public Membership update(final Membership newMembership) {
		return new Membership(membershipId,
							  newMembership.membershipType,
							  newMembership.startDate,
							  newMembership.endDate);
	}
	
	public boolean isValid(final LocalDate currentDate) {
		return (startDate.isBefore(currentDate) || startDate.isEqual(currentDate))
				&& (endDate.isAfter(currentDate) || endDate.isEqual(currentDate));
	}
	
	public MembershipId getMembershipId() {
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
