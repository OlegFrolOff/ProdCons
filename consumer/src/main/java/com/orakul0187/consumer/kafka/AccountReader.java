package com.orakul0187.consumer.kafka;

import com.orakul0187.consumer.cassandra.DataSender;
import com.orakul0187.entities.BankAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


@Component
public class AccountReader {
    @Autowired
    DataSender dataSender;

    @KafkaListener(topics = "${kafka.topic.name}", containerFactory = "kafkaListenerFactory")
    public void listener(BankAccount acc) {
        System.out.println(acc);
        dataSender.send(acc);
    }
}
