package com.hugo.java.springboot.rabbitmq.service_producer;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMqConfiguration {

    @Bean
    public TopicExchange topicExchangeOrderCreated() {
        return new TopicExchange("topic-order-created", false, true);
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
        return new Queue("topic-order-created-user-send-email-notification", false, false, true);
    }

    @Bean
    public Queue topicQueueOrderCreatedFinancialNotification() {
        return new Queue("topic-order-created-financial-notification", false, false, true);
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
        return BindingBuilder.bind(topicQueueOrderCreatedUserSendEmailNotification).to(topicExchangeOrderCreated).with("topic.order.created.#");
    }

    @Bean
    public Binding bindingTopicOrderCreatedFinancialNotification(Queue topicQueueOrderCreatedFinancialNotification, TopicExchange topicExchangeOrderCreated) {
        return BindingBuilder.bind(topicQueueOrderCreatedFinancialNotification).to(topicExchangeOrderCreated).with("topic.order.created.#");
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
