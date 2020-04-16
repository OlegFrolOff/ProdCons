package com.orakul0187;

import com.orakul0187.factories.BankAccountFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AccCreatorConf {
    @Value("${accfactory.path}")
    String path;

    @Bean
    public BankAccountFactory bankAccFactory() {
        return new BankAccountFactory(path);
    }
}
