package com.orakul0187;

import com.orakul0187.entities.BankAccount;
import com.orakul0187.factories.BankAccountFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccCreatorRestController {
    @Autowired
    BankAccountFactory bankAccFactory;

    @GetMapping("getRandomAccount")
    public BankAccount getBankAccount() {
        return bankAccFactory.getBankAccount();
    }
}
