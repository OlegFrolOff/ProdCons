package com.orakul0187;

import com.orakul0187.factories.AccountFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//TODO: не использовать задание абсолютного пути к текстовым файлам вручную
@Configuration
public class AccCreatorConf {
    @Value("${accfactory.path}")
    String path;

    @Bean
    public AccountFactory accountFactory() {
        return new AccountFactory(path);
    }
}
