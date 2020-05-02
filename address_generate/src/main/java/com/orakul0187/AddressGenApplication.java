package com.orakul0187;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafkaStreams;

@SpringBootApplication
@EnableKafkaStreams
public class AddressGenApplication {
    public static void main(String[] args) {
        SpringApplication.run(AddressGenApplication.class);
    }
}
