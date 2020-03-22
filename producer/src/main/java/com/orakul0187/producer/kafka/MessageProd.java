package com.orakul0187.producer.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;


@Component
public class MessageProd {
    @Autowired
    private KafkaTemplate<Long, String> kafkaTemplate;
    @Value(value = "${kafka.topic.name}")
    private String topicName;

    public void sendMessage(Long key, String value) {
        kafkaTemplate.send(topicName, key, value);
    }
}
