package com.orakul0187.usercassandrarequest.cassandra;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CassanrdaConfig {
    @Value("${cassandra.bootstrapAddress}")
    String bootstrapAddress;

    @Value("${cassandra.port}")
    Integer port;

    @Bean
    public CassandraConnector cassandraConnector() {
        return new CassandraConnector(bootstrapAddress, port);
    }

    @Bean
    public DataGetter dataGetter() {
        return new DataGetter();
    }
}
