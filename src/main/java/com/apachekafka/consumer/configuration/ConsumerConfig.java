package com.apachekafka.consumer.configuration;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class ConsumerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${spring.kafka.consumer.key-deserializer}")
    private String keyDeSerializer;

    @Value("${spring.kafka.consumer.value-deserializer}")
    private String valueDeSerializer;

    @Value("${spring.kafka.consumer.group-id}")
    private String groupId;

    private Properties getKafkaConsumerProperties() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", bootstrapServers);
        properties.put("key.deserializer", keyDeSerializer);
        properties.put("group.id", groupId);
        properties.put("value.deserializer", valueDeSerializer);
        properties.put("specific.avro.reader", "true");
        properties.put("schema.registry.url", "http://localhost:8081");
        return properties;
    }

    @Bean
    public KafkaConsumer<String, String> createConsumer() {
        return new KafkaConsumer<>(getKafkaConsumerProperties());
    }



}
