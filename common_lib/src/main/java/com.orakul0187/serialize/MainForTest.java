package com.orakul0187.serialize;

import com.orakul0187.entities.BankAccount;
import com.orakul0187.factories.BankAccountFactory;

public class MainForTest {
    public static void main(String[] args) {
        BankAccount bankAccount = new BankAccountFactory().getBankAccount();
        BankAccountSerializer bas = new BankAccountSerializer();
        BankAccountDeserializer bad = new BankAccountDeserializer();

        byte[] serialisedAcc = bas.serialize("topic", bankAccount);
        BankAccount deserBankAkk = bad.deserialize("topic", serialisedAcc);
        System.out.println(deserBankAkk);
    }
}
