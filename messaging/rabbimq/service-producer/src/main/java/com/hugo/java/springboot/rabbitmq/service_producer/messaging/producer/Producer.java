package com.hugo.java.springboot.rabbitmq.service_producer.messaging.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hugo.java.springboot.rabbitmq.service_producer.exception.SendCreateOrderMsgException;
import com.hugo.java.springboot.rabbitmq.service_producer.messaging.producer.dto.OrderCreatedMsgDto;
import com.hugo.java.springboot.rabbitmq.service_producer.messaging.producer.constants.ExchangeConstants;
import com.hugo.java.springboot.rabbitmq.service_producer.messaging.producer.constants.RoutingKeyConstants;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class Producer {

    ObjectMapper objectMapper;

    RabbitTemplate rabbitTemplate;

    public void sendOrderCreated(OrderCreatedMsgDto orderCreatedMsgDto) {
        try {
            String msg = objectMapper.writeValueAsString(orderCreatedMsgDto);
            rabbitTemplate.convertAndSend(ExchangeConstants.TOPIC_ORDER_CREATED, RoutingKeyConstants.TOPIC_ORDER_CREATED, msg);
            log.info("Producer.sendOrderCreated - msg sent - traceId={} - productId={}", orderCreatedMsgDto.getTraceId(), orderCreatedMsgDto.getProductId());
        } catch (Exception e) {
            log.error("Producer.sendOrderCreated - Error= {}", e.getMessage(), e);
            throw new SendCreateOrderMsgException(e, orderCreatedMsgDto.getTraceId(), orderCreatedMsgDto.getProductId());
        }
    }

}
