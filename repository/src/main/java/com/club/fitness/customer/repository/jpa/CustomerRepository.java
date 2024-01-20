package com.club.fitness.customer.repository.jpa;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.club.fitness.customer.repository.entity.CustomerEntity;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long>,
											JpaSpecificationExecutor<CustomerEntity> {
	Optional<CustomerEntity> findOneByUsername(String username);
}
