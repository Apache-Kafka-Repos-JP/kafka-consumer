package com.apachekafka.consumer;

import com.apachekafka.consumer.service.ConsumerInterface;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ConsumerApplication {


	public static void main(String[] args) {

		SpringApplication.run(ConsumerApplication.class, args);

	}

}
