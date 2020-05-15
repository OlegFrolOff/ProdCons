package com.orakul0187.consumer.kafka;

import com.orakul0187.consumer.cassandra.DataSender;
import com.orakul0187.consumer.cassandraentities.*;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaStreamsDefaultConfiguration;
import org.springframework.kafka.config.KafkaStreamsConfiguration;
import org.springframework.kafka.support.serializer.JsonSerde;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Configuration
public class KafkaConsConfig {
    @Value("${kafka.bootstrapAddress}")
    private String bootstrapAddress;

    @Value("${kafka.accounts.topic.name}")
    private String bankAccountsTopicName;

    @Value("${kafka.addresses.topic.name}")
    private String addressesTopicName;

    @Value("${kafka.output.topic.name}")
    private String outputTopicName;

    @Bean(name = KafkaStreamsDefaultConfiguration.DEFAULT_STREAMS_CONFIG_BEAN_NAME)
    public KafkaStreamsConfiguration kafkaStreamsConfiguration() {
        Map<String, Object> properties = new HashMap<>();
        properties.put(StreamsConfig.APPLICATION_ID_CONFIG, "consumer");
        properties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        return new KafkaStreamsConfiguration(properties);
    }

    @Bean
    @Autowired
    public Topology topology(StreamsBuilder streamsBuilder, DataSender dataSender) {

        JsonSerde<BankAccount> accSerde = new JsonSerde<>(BankAccount.class);
        accSerde.ignoreTypeHeaders();
        JsonSerde<Address> addressSerde  = new JsonSerde<>(Address.class);
        addressSerde.ignoreTypeHeaders();
        JsonSerde<BankAccountInfo> accInfoSerde  = new JsonSerde<>(BankAccountInfo.class);
        accInfoSerde.ignoreTypeHeaders();

        KTable<UUID, BankAccount> accountsKTable = streamsBuilder.table(bankAccountsTopicName, Consumed.with(Serdes.UUID(), accSerde));
        KTable<UUID, Address> addressesKTable = streamsBuilder.table(addressesTopicName, Consumed.with(Serdes.UUID(), addressSerde));

        accountsKTable.toStream().foreach((k, v) -> {
            System.out.println(v);
            dataSender.sendBankAccount(v);
        });



        KStream<UUID, BankAccountInfo> bankAccInfoKStream = accountsKTable
                .toStream()
                .join(addressesKTable.toStream()
                        , BankAccountInfo::new
                        , JoinWindows.of(Duration.ZERO)
                        , Joined.with(Serdes.UUID(), accSerde, addressSerde));
        bankAccInfoKStream.to(outputTopicName, Produced.with(Serdes.UUID(), accInfoSerde));

        bankAccInfoKStream.foreach((k, v) -> {
            dataSender.sendBankAccountInfo(v);
        });

        return streamsBuilder.build();
    }
}
