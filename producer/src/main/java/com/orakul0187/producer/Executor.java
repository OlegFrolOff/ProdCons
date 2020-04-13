package com.orakul0187.producer;

import com.orakul0187.entities.BankAccount;
import com.orakul0187.producer.kafka.MessageProd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
@ComponentScan("com.orakul0187")
public class Executor {
    //private MessageGen messageGen;
    private MessageProd messageProd;
    //private BankAccGrantor bankAccGrantor = new BankAccountFactory();
    //private long messageCounter = 0L;

    @Autowired
    public Executor(MessageProd messageProd) {
        this.messageProd = messageProd;
    }

    @Scheduled(fixedDelay = 500L)
    //@PostConstruct
    public void start() {
        BankAccount acc = new RestTemplate().getForObject("http://localhost:8085/getRandomAccount", BankAccount.class);
        messageProd.sendMessage(acc.getUuid(), acc);
    }


}
