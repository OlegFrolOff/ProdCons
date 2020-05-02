package com.orakul0187.kafka;

import com.orakul0187.entities.Address;
import com.orakul0187.entities.BankAccount;
import com.orakul0187.randomAPI.RandomAddressGetter;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaStreamsDefaultConfiguration;
import org.springframework.kafka.config.KafkaStreamsConfiguration;
import org.springframework.kafka.support.serializer.JsonSerde;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Configuration
public class KafkaConfig {

    @Value("${kafka.bootstrapAddress}")
    private String bootstrapAddress;

    @Value("${kafka.input.topic.name}")
    private String inputTopicName;

    @Value("${kafka.output.topic.name}")
    private String outputTopicName;

    @Autowired
    RandomAddressGetter randomAddressGetter;

    private KafkaStreams accountsKstream;

    @Bean(name = KafkaStreamsDefaultConfiguration.DEFAULT_STREAMS_CONFIG_BEAN_NAME)
    public KafkaStreamsConfiguration kafkaStreamsConfiguration() {
        Map<String, Object> properties = new HashMap<>();
        properties.put(StreamsConfig.APPLICATION_ID_CONFIG, "address_generate");
        properties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        return new KafkaStreamsConfiguration(properties);
    }

    @Bean
    public Topology topology(StreamsBuilder streamsBuilder) {
        KTable<UUID, BankAccount> accountsKTable = streamsBuilder.table(inputTopicName, Consumed.with(Serdes.UUID(), new JsonSerde<>(BankAccount.class)));
        KStream<UUID, Address> addressKStream = accountsKTable.
                toStream().
                filter((uuid, bankAccount) -> bankAccount.getLastName().startsWith("А")).
                flatMapValues((v) -> randomAddressGetter.getAddress(1));
        addressKStream.to(outputTopicName, Produced.with(Serdes.UUID(), new JsonSerde<>(Address.class)));
        return streamsBuilder.build();
    }

//    @PostConstruct
//    private void startService(){
//        Properties prop = new Properties();
//        prop.put(StreamsConfig.APPLICATION_ID_CONFIG, "address_generate");
//        prop.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
//
//        StreamsBuilder builder = new StreamsBuilder();
//
//        KTable<UUID, BankAccount> accountsKTable = builder.table(inputTopicName, Consumed.with(Serdes.UUID(), new JsonSerde<>(BankAccount.class)));
//        KStream<UUID, Address> addressKStream = accountsKTable.
//                toStream().
//                filter((uuid, bankAccount) -> bankAccount.getLastName().startsWith("А")).
//                flatMapValues((v) -> randomAddressGetter.getAddress(1));
//        addressKStream.to(outputTopicName, Produced.with(Serdes.UUID(), new JsonSerde<>(Address.class)));
//        accountsKstream = new KafkaStreams(builder.build(), prop);
//        accountsKstream.start();
//    }
//
//    @PreDestroy
//    private void stopServ(){
//        accountsKstream.close();
//    }
}
