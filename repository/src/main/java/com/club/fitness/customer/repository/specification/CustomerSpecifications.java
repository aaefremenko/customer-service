package com.club.fitness.customer.repository.specification;

import org.springframework.data.jpa.domain.Specification;

import com.club.fitness.customer.repository.entity.CustomerEntity;

public class CustomerSpecifications {
	
	public static Specification<CustomerEntity> hasUsername(final String username) {
		return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("username"), username);
	}
	
	public static Specification<CustomerEntity> hasFirstName(final String firstName) {
		return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("firstName"), firstName);
	}
	
	public static Specification<CustomerEntity> hasLastName(final String lastName) {
		return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("lastName"), lastName);
	}
	
	public static Specification<CustomerEntity> hasAddress(final String address) {
		return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("address"), address);
	}
	
	public static Specification<CustomerEntity> hasPhoneNumber(final String phoneNumber) {
		return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("phoneNumber"), phoneNumber);
	}
	
	public static Specification<CustomerEntity> hasEmail(final String email) {
		return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("email"), email);
	}
}
