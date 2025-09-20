package com.hugo.java.springboot.rabbitmq.service_producer;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMqConfiguration {

    @Bean
    public TopicExchange topicExchangeOrderCreated() {
        return new TopicExchange("topic-order-created", true, true);
    }

    /*
    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange("direct-exchange-order-created", true, true);
    }

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanout-exchange", true, true);
    }
    */

}
