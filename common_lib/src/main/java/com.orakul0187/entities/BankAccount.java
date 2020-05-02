package com.orakul0187.entities;

import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
public class BankAccount implements Serializable {
    private UUID uuid;
    private String firstName;
    private String lastName;
    private String patronymic;
    private long accountNumber;

    public BankAccount() {
    }

    public BankAccount(UUID uuid, String firstName, String lastName, String patronymic, long accountNumber) {
        this.uuid = uuid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.accountNumber = accountNumber;
    }
}
