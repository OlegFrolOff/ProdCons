package com.orakul0187.consumer.kafka;

import com.orakul0187.entities.BankAccount;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


@Component
@ComponentScan("com.orakul0187")
public class AccountReader {

    @KafkaListener(topics = "${kafka.topic.name}", containerFactory = "kafkaListenerFactory")
    public void listener(BankAccount acc) {
        System.out.println(acc + "\n");
    }
}
