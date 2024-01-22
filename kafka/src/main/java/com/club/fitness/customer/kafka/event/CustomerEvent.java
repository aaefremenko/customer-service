package com.club.fitness.customer.kafka.event;

public record CustomerEvent(Long customerId, String type) {

}
