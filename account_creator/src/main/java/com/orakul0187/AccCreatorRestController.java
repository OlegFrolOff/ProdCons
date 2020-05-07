package com.orakul0187;

import com.orakul0187.entities.BankAccount;
import com.orakul0187.factories.BankAccountFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/")
public class AccCreatorRestController {
    @Autowired
    BankAccountFactory bankAccFactory;

    @RequestMapping("/getRandomAccount")
    public BankAccount getBankAccount() {
        return bankAccFactory.getBankAccount();
    }
}
