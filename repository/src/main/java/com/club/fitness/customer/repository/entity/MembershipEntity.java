package com.club.fitness.customer.repository.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "memberships")
public class MembershipEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "type")
	private String type;
	
	@Column(name = "start_date", columnDefinition = "DATE")
	private LocalDate startDate;
	
	@Column(name = "end_date", columnDefinition = "DATE")
	private LocalDate endDate;
	
	public MembershipEntity() {}
	
	public MembershipEntity(final Long id,
							final String type,
							final LocalDate startDate,
							final LocalDate endDate) {
		this.id = id;
		this.type = type;
		this.startDate = startDate;
		this.endDate = endDate;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	
}
