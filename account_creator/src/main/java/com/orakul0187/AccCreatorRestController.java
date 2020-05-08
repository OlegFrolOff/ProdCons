package com.orakul0187;

import com.orakul0187.entities.Account;
import com.orakul0187.factories.AccountFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/")
public class AccCreatorRestController {
    @Autowired
    AccountFactory bankAccFactory;

    @RequestMapping("/getRandomAccount")
    public Account getBankAccount() {
        return bankAccFactory.getAccount();
    }
}
