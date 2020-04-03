package com.orakul0187.serialize;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.orakul0187.entities.BankAccount;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.IOException;

public class BankAccountDeserializer implements Deserializer<BankAccount> {

    @Override
    public BankAccount deserialize(String s, byte[] bytes) {
        BankAccount acc = null;

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            acc = objectMapper.readValue(bytes, BankAccount.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return acc;
    }
}
