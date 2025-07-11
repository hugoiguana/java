package com.hugo.java.springboot.rabbitmq.service_producer;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

	AmqpTemplate amqpTemplate;

	public Application(AmqpTemplate amqpTemplate) {
		this.amqpTemplate = amqpTemplate;
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		amqpTemplate.convertAndSend("topic-exchange", "topic.key.1", "Message for topic");
		amqpTemplate.convertAndSend("direct-exchange", "direct.key", "Message for direct");
		amqpTemplate.convertAndSend("fanout-exchange", "", "Message for fanout");
	}

}
