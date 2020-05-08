package com.orakul0187.entities;

import com.orakul0187.enums.AccountType;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
public class BankAccount extends Account implements Serializable {

    private AccountType accountType;

    public BankAccount() {
    }

    public BankAccount(Account account, AccountType accountType) {
        super.setUuid(account.getUuid());
        super.setFirstName(account.getFirstName());
        super.setLastName(account.getLastName());
        super.setPatronymic(account.getPatronymic());
        super.setAccountNumber(account.getAccountNumber());
        this.accountType = accountType;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder("BankAccount(");
        sb.append("UUID = ").append(super.getUuid()).append(" ; ");
        sb.append("FirstName = ").append(super.getFirstName()).append(" ; ");
        sb.append("Patronymic = ").append(super.getPatronymic()).append(" ; ");
        sb.append("LastName = ").append(super.getLastName()).append(" ; ");
        sb.append("AccountNumber = ").append(super.getAccountNumber()).append(" ; ");
        sb.append("AccountType = ").append(accountType).append(")");
        return sb.toString();
    }
}
