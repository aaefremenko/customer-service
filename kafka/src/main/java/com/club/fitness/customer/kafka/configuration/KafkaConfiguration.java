package com.club.fitness.customer.kafka.configuration;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.club.fitness.customer.kafka.event.CustomerEvent;

@Configuration
public class KafkaConfiguration {
	
	private final KafkaProperties kafkaProperties;
	private final String topicName;
	
	public KafkaConfiguration(final KafkaProperties kafkaProperties,
							  final @Value("${kafka.topic-name}") String topicName) {
		this.kafkaProperties = kafkaProperties;
		this.topicName = topicName;
	}
	
	@Bean
    Map<String, Object> producerConfigs() {
        final Map<String, Object> props = new HashMap<>(kafkaProperties.buildProducerProperties());
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return props;
    }

    @Bean
    ProducerFactory<String, CustomerEvent> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    KafkaTemplate<String, CustomerEvent> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    @Bean
    NewTopic topic() {
        return new NewTopic(topicName, 1, (short) 1);
    }

}
