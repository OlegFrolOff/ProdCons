package com.orakul0187.consumer.cassandra;
import com.orakul0187.consumer.cassandraentities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;


@Service
public class DataSender {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Autowired
    private  BankAccountInfoRepository bankAccountInfoRepository;

    @PostConstruct
    public void init() {
        bankAccountRepository.deleteAll();
        bankAccountInfoRepository.deleteAll();
    }


    public void sendBankAccount(BankAccount bankAccount) {
        bankAccountRepository.save(bankAccount);
    }

    public void sendBankAccountInfo(BankAccountInfo bankAccountInfo) {
        bankAccountInfoRepository.save(bankAccountInfo);
    }
}
