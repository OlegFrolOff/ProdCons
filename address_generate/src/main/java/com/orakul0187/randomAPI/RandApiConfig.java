package com.orakul0187.randomAPI;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RandApiConfig {
    @Bean
    public RandomAddressGetter randomAddressGetter() {
        return new RandomAddressGetter();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
