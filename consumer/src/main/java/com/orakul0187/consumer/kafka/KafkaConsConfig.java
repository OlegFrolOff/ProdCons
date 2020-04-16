package com.orakul0187.consumer.kafka;

import com.orakul0187.entities.BankAccount;
import com.orakul0187.serialize.BankAccountDeserializer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.UUIDDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Configuration
public class KafkaConsConfig {
    @Value("${kafka.bootstrapAddress}")
    private String bootstrapAddress;

    public ConsumerFactory<UUID, BankAccount> consFactory() {
        Map<String, Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "consuming");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, UUIDDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, BankAccountDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(config);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<UUID, BankAccount> kafkaListenerFactory() {
        ConcurrentKafkaListenerContainerFactory<UUID, BankAccount> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consFactory());
        return factory;
    }
}
