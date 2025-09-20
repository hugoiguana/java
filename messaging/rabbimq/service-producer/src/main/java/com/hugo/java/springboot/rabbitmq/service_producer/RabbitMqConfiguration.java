package com.hugo.java.springboot.rabbitmq.service_producer;

import com.hugo.java.springboot.rabbitmq.service_producer.messaging.producer.constants.ExchangeConstants;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMqConfiguration {

    @Bean
    public TopicExchange topicExchangeOrderCreated() {
        return new TopicExchange(ExchangeConstants.TOPIC_ORDER_CREATED, true, false);
    }


    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(ExchangeConstants.DIRECT_ORDER_CREATED, true, false);
    }

    /*
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanout-exchange", true, true);
    }
    */

}
