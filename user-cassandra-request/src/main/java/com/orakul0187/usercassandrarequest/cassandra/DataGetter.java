package com.orakul0187.usercassandrarequest.cassandra;

import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.UUID;

public class DataGetter {
    @Value("${cassandra.prepQuery}")
    String prepQuery;

    @Autowired
    private CassandraConnector cassandraConnector;
    private Session session;
    private PreparedStatement ps;

    @PostConstruct
    public void init(){
        session = cassandraConnector.getSession();
        session.execute("USE tcproject;");
        ps = session.prepare(prepQuery);
    }

    public String getAccountData(UUID uuid){
        ResultSet rs = session.execute(ps.bind(uuid));
        List<Row> rows = rs.all();
        if(rows.size()==1){
            return rows.get(0).toString();
        } else {
            return null;
        }
    }
}
