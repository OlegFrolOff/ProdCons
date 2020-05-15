package com.orakul0187.consumer.cassandraentities;

import com.datastax.driver.core.DataType;
import lombok.Data;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.io.Serializable;
import java.util.UUID;

@Data
@Table
public class BankAccountInfo implements Serializable {
    @PrimaryKeyColumn(type = PrimaryKeyType.PARTITIONED)
    private UUID uuid;
    @CassandraType(type = DataType.Name.UDT, userTypeName = "bankaccount")
    private BankAccount bankAccount;
    @CassandraType(type = DataType.Name.UDT, userTypeName = "address")
    private Address address;

    protected BankAccountInfo() {
    }

    public BankAccountInfo(BankAccount bankAccount, Address address) {
        this.uuid = bankAccount.getUuid();
        this.bankAccount = bankAccount;
        this.address = address;
    }
}
