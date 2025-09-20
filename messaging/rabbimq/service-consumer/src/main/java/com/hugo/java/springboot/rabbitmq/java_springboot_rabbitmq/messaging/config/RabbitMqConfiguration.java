package com.hugo.java.springboot.rabbitmq.java_springboot_rabbitmq.messaging.config;

import com.hugo.java.springboot.rabbitmq.java_springboot_rabbitmq.messaging.enums.ExchangeConstants;
import com.hugo.java.springboot.rabbitmq.java_springboot_rabbitmq.messaging.enums.QueueConstants;
import com.hugo.java.springboot.rabbitmq.java_springboot_rabbitmq.messaging.enums.RoutingKeyConstants;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMqConfiguration {

    @Bean
    public TopicExchange topicExchangeOrderCreated() {
        return new TopicExchange(ExchangeConstants.TOPIC_ORDER_CREATED, true, true);
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

    @Bean
    public Queue topicQueueOrderCreatedUserSendEmailNotification() {
        return new Queue(QueueConstants.TOPIC_ORDER_CREATED_USER_SEND_EMAIL_NOTIFICATION, true, false, true);
    }

    @Bean
    public Queue topicQueueOrderCreatedFinancialNotification() {
        return new Queue(QueueConstants.TOPIC_ORDER_CREATED_FINANCIAL_NOTIFICATION, true, false, true);
    }


    /*
    @Bean
    public Queue directQueue() {
        return new Queue("direct.queue", true, false, true);
    }

    @Bean
    public Queue fanoutQueue() {
        return new Queue("fanout.queue", true, false, true);
    }
    */

    @Bean
    public Binding bindingTopiOrderCreatedUserNotification(Queue topicQueueOrderCreatedUserSendEmailNotification, TopicExchange topicExchangeOrderCreated) {
        return BindingBuilder.bind(topicQueueOrderCreatedUserSendEmailNotification).to(topicExchangeOrderCreated).with(RoutingKeyConstants.TOPIC_ORDER_CREATED);
    }

    @Bean
    public Binding bindingTopicOrderCreatedFinancialNotification(Queue topicQueueOrderCreatedFinancialNotification, TopicExchange topicExchangeOrderCreated) {
        return BindingBuilder.bind(topicQueueOrderCreatedFinancialNotification).to(topicExchangeOrderCreated).with(RoutingKeyConstants.TOPIC_ORDER_CREATED);
    }


    /*
    @Bean
    public Binding bindingDirect(Queue directQueue, DirectExchange directExchange) {
        return BindingBuilder.bind(directQueue).to(directExchange).with("direct.key");
    }

    @Bean
    public Binding bindingFanout(Queue fanoutQueue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanoutQueue).to(fanoutExchange);
    }
    */

}
