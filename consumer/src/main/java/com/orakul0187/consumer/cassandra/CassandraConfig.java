package com.orakul0187.consumer.cassandra;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.CassandraClusterFactoryBean;
import org.springframework.data.cassandra.core.mapping.CassandraMappingContext;
import org.springframework.data.cassandra.core.mapping.SimpleUserTypeResolver;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

@Configuration
@EnableCassandraRepositories(basePackages = "com.orakul0187.consumer.cassandraentities")
public class CassandraConfig extends AbstractCassandraConfiguration {
    @Value("${spring.data.cassandra.keyspace-name}")
    String keyspace;

    @Value("${cassandra.bootstrapAddress}")
    String bootstrapAddress;

    @Value("${cassandra.port}")
    int port;

    @Bean
    public CassandraClusterFactoryBean cluster() {
        CassandraClusterFactoryBean cluster = new CassandraClusterFactoryBean();
        cluster.setContactPoints(bootstrapAddress);
        cluster.setPort(port);
        cluster.setJmxReportingEnabled(false);
        return cluster;
    }

    @Bean
    public CassandraMappingContext cassandraMapping() throws ClassNotFoundException {
        CassandraMappingContext mappingContext = new CassandraMappingContext();
        mappingContext.setInitialEntitySet(getInitialEntitySet());
        mappingContext.setUserTypeResolver(new SimpleUserTypeResolver(cluster().getObject(), keyspace));
        return mappingContext;
    }

    @Override
    protected String getKeyspaceName() {
        return keyspace;
    }
}
