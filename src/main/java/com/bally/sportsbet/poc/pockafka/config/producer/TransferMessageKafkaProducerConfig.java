/**
 * 
 */
package com.bally.sportsbet.poc.pockafka.config.producer;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

/**
 * @author Orlei Bicheski
 *
 */
@Configuration
public class TransferMessageKafkaProducerConfig {

	@Value(value = "${kafka.bootstrap-address}")
	private String bootstrapAddress;

	@Bean
	public ProducerFactory<String, String> producerFactoryString() {
		Map<String, Object> configProps = new HashMap<>();
		configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
		configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

		return new DefaultKafkaProducerFactory<String, String>(configProps);
	}

	@Bean
	public KafkaTemplate<String, String> kafkaTemplateString(ProducerFactory<String, String> producerFactoryString) {
		return new KafkaTemplate<String, String>(producerFactoryString);
	}

}
