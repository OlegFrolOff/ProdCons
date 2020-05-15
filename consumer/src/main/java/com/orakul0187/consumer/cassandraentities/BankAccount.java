package com.orakul0187.consumer.cassandraentities;

import com.datastax.driver.core.DataType;
import lombok.Data;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;

import java.io.Serializable;
import java.util.UUID;

@Data
@Table
@UserDefinedType("bankaccount")
public class BankAccount implements Serializable {
    @PrimaryKeyColumn(type = PrimaryKeyType.PARTITIONED)
    @CassandraType(type = DataType.Name.UUID)
    private UUID uuid;
    @CassandraType(type = DataType.Name.TEXT)
    private String firstName;
    @CassandraType(type = DataType.Name.TEXT)
    private String lastName;
    @CassandraType(type = DataType.Name.TEXT)
    private String patronymic;
    @CassandraType(type = DataType.Name.BIGINT)
    private long accountNumber;
    @CassandraType(type = DataType.Name.TEXT)
    private AccountType accountType;

    public BankAccount() {
    }
}
