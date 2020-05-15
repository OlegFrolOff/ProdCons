package com.orakul0187.entities;

import com.orakul0187.enums.AccountType;
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
    private AccountType accountType;

    public BankAccount() {
    }

    public BankAccount(Account account, AccountType accountType) {
        this.uuid = account.getUuid();
        this.firstName = account.getFirstName();
        this.lastName = account.getLastName();
        this.patronymic = account.getPatronymic();
        this.accountNumber = account.getAccountNumber();
        this.accountType = accountType;
    }
}
