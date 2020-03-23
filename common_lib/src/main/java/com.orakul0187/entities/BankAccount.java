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
}
