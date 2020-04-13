package com.orakul0187.entities;

import java.io.Serializable;
import java.util.UUID;

public class BankAccount implements Serializable {
    private UUID uuid;
    private String firstName;
    private String lastName;
    private String patronymic;
    private int accountNumber;

    public BankAccount() {
    }

    public BankAccount(UUID uuid, String firstName, String lastName, String patronymic, int accountNumber) {
        this.uuid = uuid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.accountNumber = accountNumber;
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "uuid=" + uuid +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", accountNumber=" + accountNumber +
                '}';
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public long getAccountNumber() {
        return accountNumber;
    }
}
