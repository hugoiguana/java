package com.hugo.java.springboot.rabbitmq.service_producer;

import com.hugo.java.springboot.rabbitmq.service_producer.messaging.producer.Producer;
import com.hugo.java.springboot.rabbitmq.service_producer.messaging.producer.dto.OrderCreatedMsgDto;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.UUID;

@SpringBootApplication
public class Application implements CommandLineRunner {

    Producer producer;

	public Application(Producer producer) {
		this.producer = producer;
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

        producer.sendOrderCreated(
                OrderCreatedMsgDto.builder()
                        .traceId(UUID.randomUUID())
                        .productId(UUID.randomUUID())
                        .quantity(2)
                        .build()
        );

	}

}
