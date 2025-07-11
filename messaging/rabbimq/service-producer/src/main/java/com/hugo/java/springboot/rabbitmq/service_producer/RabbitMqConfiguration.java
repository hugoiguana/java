package com.hugo.java.springboot.rabbitmq.service_producer;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMqConfiguration {

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange("topic-exchange");
    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange("direct-exchange");
    }

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanout-exchange");
    }

    @Bean
    public Queue topicQueue() {
        return new Queue("topic.queue");
    }

    @Bean
    public Queue directQueue() {
        return new Queue("direct.queue");
    }

    @Bean
    public Queue fanoutQueue() {
        return new Queue("fanout.queue");
    }

    @Bean
    public Binding bindingTopic(Queue topicQueue, TopicExchange topicExchange) {
        return BindingBuilder.bind(topicQueue).to(topicExchange).with("topic.key.#");
    }

    @Bean
    public Binding bindingDirect(Queue directQueue, DirectExchange directExchange) {
        return BindingBuilder.bind(directQueue).to(directExchange).with("direct.key");
    }

    @Bean
    public Binding bindingFanout(Queue fanoutQueue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanoutQueue).to(fanoutExchange);
    }

}
