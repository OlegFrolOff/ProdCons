package com.orakul0187.consumer.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;


@Component
@ComponentScan("com.orakul0187")
public class MessageReader {

    @KafkaListener(topics = "${kafka.topic.name}", containerFactory = "kafkaListenerFactory")
    public void listener(String message) {
        System.out.println("Get message:\n" + message);
    }
}
