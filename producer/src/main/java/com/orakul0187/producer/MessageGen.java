package com.orakul0187.producer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application.properties")
public class MessageGen {
    @Value("${text}")
    private String text;

    public String getMessage(){
        StringBuilder message = new StringBuilder();
        int beginTextIndex = Rand.randomInt(0, this.text.length() - 500);
        int endTextIndex = Rand.randomInt(beginTextIndex + 100, beginTextIndex + 500);
        String[] splitText = this.text.substring(beginTextIndex, endTextIndex).split("\\.");
        for (int i = 1; i < splitText.length; i++) {
            message.append(splitText[i]).append(".");
        }
        return message.toString();
    }
}
