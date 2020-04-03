package com.orakul0187.serialize;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.orakul0187.entities.BankAccount;
import org.apache.kafka.common.serialization.Serializer;

public class BankAccountSerializer implements Serializer<BankAccount> {

    @Override
    public byte[] serialize(String s, BankAccount bankAccount) {
        byte[] retVal = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            retVal = objectMapper.writeValueAsString(bankAccount).getBytes();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retVal;
    }
}
