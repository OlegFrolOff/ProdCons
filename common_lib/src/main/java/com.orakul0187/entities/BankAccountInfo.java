package com.orakul0187.entities;


import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
public class BankAccountInfo implements Serializable {
    private UUID uuid;
    private BankAccount bankAccount;
    private Address address;

    protected BankAccountInfo() {
    }

    public BankAccountInfo(BankAccount bankAccount, Address address) {
        this.uuid = bankAccount.getUuid();
        this.bankAccount = bankAccount;
        this.address = address;
    }
}
