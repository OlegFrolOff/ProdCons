package com.orakul0187.producer;

import com.orakul0187.producer.kafka.MessageProd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
@ComponentScan("com.orakul0187")
public class Executor {
    private MessageGen messageGen;
    private MessageProd messageProd;
    private long messageCounter = 0;

    @Autowired
    public Executor(MessageGen messageGen, MessageProd messageProd) {
        this.messageGen = messageGen;
        this.messageProd = messageProd;
    }

    @Scheduled(fixedDelay = 500L)
    public void start() {
            messageProd.sendMessage(++messageCounter, messageGen.getMessage());
    }


}
