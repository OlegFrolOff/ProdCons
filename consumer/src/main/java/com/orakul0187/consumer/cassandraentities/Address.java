package com.orakul0187.consumer.cassandraentities;

import com.datastax.driver.core.DataType;
import lombok.Data;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;

import java.io.Serializable;

@Data
@UserDefinedType("address")
public class Address implements Serializable {
    @CassandraType(type = DataType.Name.TEXT)
    String street;
    @CassandraType(type = DataType.Name.TEXT)
    String city;
    @CassandraType(type = DataType.Name.TEXT)
    String state;
}
