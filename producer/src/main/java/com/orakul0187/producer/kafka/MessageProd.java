package com.orakul0187.producer.kafka;

import com.orakul0187.entities.BankAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;


@Component
public class MessageProd {
    @Autowired
    private KafkaTemplate<UUID, BankAccount> kafkaTemplate;
    @Value(value = "${kafka.topic.name}")
    private String topicName;

    public void sendMessage(UUID key, BankAccount value) {
        kafkaTemplate.send(topicName, key, value);
    }
}
