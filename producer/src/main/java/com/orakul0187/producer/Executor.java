package com.orakul0187.producer;

import com.orakul0187.entities.BankAccount;
import com.orakul0187.factories.BankAccGrantor;
import com.orakul0187.factories.BankAccountFactory;
import com.orakul0187.producer.kafka.MessageProd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


@Component
@ComponentScan("com.orakul0187")
public class Executor {
    //    private MessageGen messageGen;
    private MessageProd messageProd;
    private BankAccGrantor bankAccGrantor = new BankAccountFactory();
//    private long messageCounter = 0L;

    @Autowired
    public Executor(MessageGen messageGen, MessageProd messageProd) {
        //this.messageGen = messageGen;
        this.messageProd = messageProd;
    }

    @Scheduled(fixedDelay = 500L)
    //@PostConstruct
    public void start() {
        BankAccount acc = bankAccGrantor.getBankAccount();
        messageProd.sendMessage(acc.getUuid(), acc);
    }


}
