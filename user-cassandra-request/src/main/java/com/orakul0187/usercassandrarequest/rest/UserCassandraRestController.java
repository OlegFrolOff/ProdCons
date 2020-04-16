package com.orakul0187.usercassandrarequest.rest;

import com.orakul0187.entities.BankAccount;
import com.orakul0187.usercassandrarequest.cassandra.DataGetter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class UserCassandraRestController {
    @Autowired
    DataGetter dataGetter;

    @GetMapping("getAccount")
    public String getAccount(@RequestParam String uuid){
        String response = dataGetter.getAccountData(UUID.fromString(uuid));
        return response == null ? "Account not found or more than one account found" : response;
    }
}
