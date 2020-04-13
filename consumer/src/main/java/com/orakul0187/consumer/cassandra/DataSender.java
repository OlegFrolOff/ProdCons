package com.orakul0187.consumer.cassandra;

import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.Session;
import com.orakul0187.entities.BankAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;


public class DataSender {

    @Autowired
    private CassandraConnector cassandraConnector;

    private Session session;
    private PreparedStatement ps;
    private final String CLEAR_TABLE = "TRUNCATE TABLE bankAccounts;";

    @PostConstruct
    public void clearTable(){
        session = cassandraConnector.getSession();
        session.execute("USE tcproject;");
        ps = session.prepare("INSERT INTO bankAccounts(id, accnumber, firstname, lastname, patronymic) VALUES(?,?,?,?,?);");
        session.execute(CLEAR_TABLE);
    }

    public void send(BankAccount bankAccount){
        session.execute(ps.bind(bankAccount.getUuid(), (int)bankAccount.getAccountNumber(), bankAccount.getFirstName(), bankAccount.getLastName(), bankAccount.getPatronymic()));
    }
}
