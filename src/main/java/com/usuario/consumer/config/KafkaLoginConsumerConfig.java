package com.usuario.consumer.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.usuario.consumer.domain.Login;

@Configuration
public class KafkaLoginConsumerConfig {

	@Value(value = "${spring.kafka.bootstrap-servers}")
	private String bootstrapAddress;

	@Value(value = "${spring.kafka.group-id}")
	private String groupId;

	@Bean
	public ConsumerFactory<String, Login> loginConsumerFactory() {
		Map<String, Object> props = new HashMap<>();
		
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
		props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		
		return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(),
				new JsonDeserializer<>(Login.class, false));
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, Login> loginKafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, Login> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(loginConsumerFactory());
		return factory;
	}
}
