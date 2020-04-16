package com.orakul0187.producer;

import com.orakul0187.entities.BankAccount;
import com.orakul0187.producer.kafka.MessageProd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class Executor {
    private MessageProd messageProd;

    @Autowired
    public Executor(MessageProd messageProd) {
        this.messageProd = messageProd;
    }

    @Scheduled(fixedDelay = 500L)
    public void start() {
        BankAccount acc = new RestTemplate().getForObject("http://localhost:8085/getRandomAccount", BankAccount.class);
        System.out.println(acc);
        messageProd.sendMessage(acc.getUuid(), acc);
    }


}
