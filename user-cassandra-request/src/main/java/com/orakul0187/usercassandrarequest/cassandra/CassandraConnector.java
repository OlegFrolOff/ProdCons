package com.orakul0187.usercassandrarequest.cassandra;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;

import javax.annotation.PreDestroy;

public class CassandraConnector {
    private Cluster cluster;
    private Session session;

    public CassandraConnector(String bootstrapAddress, Integer port){
        Cluster.Builder builder = Cluster.builder().addContactPoint(bootstrapAddress);
        if(port != null){
            builder.withPort(port);
        }
        builder.withoutJMXReporting();
        this.cluster = builder.build();
        this.session = cluster.connect();
    }

    public Session getSession() {
        return this.session;
    }

    @PreDestroy
    public void close(){
        session.close();
        cluster.close();
    }
}
