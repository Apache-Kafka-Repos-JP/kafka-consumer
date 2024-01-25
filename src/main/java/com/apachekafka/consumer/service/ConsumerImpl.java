package com.apachekafka.consumer.service;

import com.apachekafka.consumer.SuggestionEngine;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.stereotype.Service;
import java.time.Duration;

import static java.util.Arrays.asList;

@Service
@Slf4j
@RequiredArgsConstructor
public class ConsumerImpl implements ConsumerInterface {

    private final KafkaConsumer<String, String> kafkaConsumer;

    @Override
    @PostConstruct
    public void run() {

        SuggestionEngine suggestionEngine = new SuggestionEngine();

        kafkaConsumer.subscribe(asList("user-tracking-avro"));

        while (true) {
            ConsumerRecords<String, String> records = kafkaConsumer.poll(Duration.ofMillis(20000));

            for (ConsumerRecord<String, String> record : records) {
                suggestionEngine.processSuggestions(record.key(), record.value());
            }
        }

    }

}
