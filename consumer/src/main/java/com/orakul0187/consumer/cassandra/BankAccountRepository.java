package com.orakul0187.consumer.cassandra;
import com.orakul0187.consumer.cassandraentities.BankAccount;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface BankAccountRepository extends CassandraRepository<BankAccount, UUID> {

}
