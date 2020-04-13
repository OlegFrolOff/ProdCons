package com.orakul0187.consumer.cassandra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CassandraConfig {
    @Value("${cassandra.bootstrapAddress}")
    String bootstrapAddress;

    @Value("${cassandra.port}")
    Integer port;

    @Bean
    public CassandraConnector cassandraConnector() {
        return new CassandraConnector(bootstrapAddress, port);
    }

    @Bean
    public DataSender dataSender() {
        return new DataSender();
    }
}
