package com.orakul0187.randomAPI;

import com.orakul0187.entities.Address;
import com.orakul0187.entities.RandomApiResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class RandomAddressGetter {
    @Value("${randomapi.address}")
    String randApiAddress;
    @Value("${randomapi.key}")
    String key;
    @Value("${randomapi.refId}")
    String refId;
    @Value("${randomapi.noinfo}")
    String noinfo;
    @Value("${randomapi.quantity}")
    String results;
    @Autowired
    RestTemplate rt;

    public List<Address> getAddress(int quantity) {
        RandomApiResponseEntity res = rt.getForObject(randApiAddress + key + refId + noinfo + results + quantity, RandomApiResponseEntity.class);
        res.getResults().forEach(System.out::println);
        return res.getResults();
    }
}
