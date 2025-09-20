package com.hugo.java.springboot.rabbitmq.java_springboot_rabbitmq.messaging.config;

import com.hugo.java.springboot.rabbitmq.java_springboot_rabbitmq.messaging.enums.ExchangeConstants;
import com.hugo.java.springboot.rabbitmq.java_springboot_rabbitmq.messaging.enums.QueueConstants;
import com.hugo.java.springboot.rabbitmq.java_springboot_rabbitmq.messaging.enums.RoutingKeyConstants;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;


@Configuration
public class RabbitMqConfiguration {

    @Bean
    public TopicExchange topicExchangeOrderCreated() {
        return new TopicExchange(ExchangeConstants.TOPIC_ORDER_CREATED, true, false);
    }


    @Bean
    public DirectExchange directExchangeOrderCreated() {
        return new DirectExchange(ExchangeConstants.DIRECT_ORDER_CREATED, true, false);
    }

    @Bean
    public FanoutExchange fanoutExchangeOrderCreated() {
        return new FanoutExchange(ExchangeConstants.FANOUT_ORDER_CREATED, true, true);
    }



    @Bean
    public Queue topicQueueOrderCreatedUserSendEmailNotification() {
        return new Queue(QueueConstants.TOPIC_ORDER_CREATED_USER_SEND_EMAIL_NOTIFICATION, true, false, false);
    }

    @Bean
    public Queue topicQueueOrderCreatedFinancialNotification() {
        return new Queue(QueueConstants.TOPIC_ORDER_CREATED_FINANCIAL_NOTIFICATION, true, false, false);
    }

    @Bean
    public Queue directQueueOrderCreatedFinancialNotification() {
        return new Queue(QueueConstants.DIRECT_ORDER_CREATED_FINANCIAL_NOTIFICATION, true, false, false);
    }

    @Bean
    public Queue fanoutQueueOrderCreatedFinancialNotification() {
        return new Queue(String.format("%s-%s", QueueConstants.FANOUT_ORDER_CREATED_FINANCIAL_NOTIFICATION, UUID.randomUUID()), false, false, true);
    }


    @Bean
    public Binding bindingTopiOrderCreatedUserNotification(Queue topicQueueOrderCreatedUserSendEmailNotification, TopicExchange topicExchangeOrderCreated) {
        return BindingBuilder.bind(topicQueueOrderCreatedUserSendEmailNotification).to(topicExchangeOrderCreated).with(RoutingKeyConstants.TOPIC_ORDER_CREATED);
    }

    @Bean
    public Binding bindingTopicOrderCreatedFinancialNotification(Queue topicQueueOrderCreatedFinancialNotification, TopicExchange topicExchangeOrderCreated) {
        return BindingBuilder.bind(topicQueueOrderCreatedFinancialNotification).to(topicExchangeOrderCreated).with(RoutingKeyConstants.TOPIC_ORDER_CREATED);
    }


    @Bean
    public Binding bindingDirectOrderCreatedFinancialNotification(Queue directQueueOrderCreatedFinancialNotification, DirectExchange directExchangeOrderCreated) {
        return BindingBuilder.bind(directQueueOrderCreatedFinancialNotification).to(directExchangeOrderCreated).with(RoutingKeyConstants.DIRECT_ORDER_CREATED);
    }


    @Bean
    public Binding bindingFanoutOrderCreatedFinancialNotification(Queue fanoutQueueOrderCreatedFinancialNotification, FanoutExchange fanoutExchangeOrderCreated) {
        return BindingBuilder.bind(fanoutQueueOrderCreatedFinancialNotification).to(fanoutExchangeOrderCreated);
    }

}
