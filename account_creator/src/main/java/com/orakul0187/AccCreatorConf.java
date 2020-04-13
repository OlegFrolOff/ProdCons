package com.orakul0187;

import com.orakul0187.factories.BankAccountFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AccCreatorConf {
    @Bean
    public BankAccountFactory bankAccFactory() {
        return new BankAccountFactory();
    }
}
