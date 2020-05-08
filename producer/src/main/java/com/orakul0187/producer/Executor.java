package com.orakul0187.producer;

import com.orakul0187.entities.Account;
import com.orakul0187.entities.BankAccount;
import com.orakul0187.enums.AccountType;
import com.orakul0187.other.Rand;
import com.orakul0187.producer.kafka.MessageProd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class Executor {
    @Autowired
    private MessageProd messageProd;

    @Scheduled(fixedDelay = 500L)
    public void start() {
        Account account = new RestTemplate().getForEntity("http://localhost:8085/getRandomAccount", Account.class).getBody();
        BankAccount acc = new BankAccount(account, getRandomType());
        System.out.println(acc);
        messageProd.sendMessage(acc.getUuid(), acc);
    }

    private AccountType getRandomType(){
        AccountType allTypes[] = AccountType.values();
        return allTypes[Rand.randomInt(0, allTypes.length-1)];
    }
}
