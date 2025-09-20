package com.hugo.java.springboot.rabbitmq.java_springboot_rabbitmq.messaging.consumers;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.hugo.java.springboot.rabbitmq.java_springboot_rabbitmq.messaging.dto.OrderCreatedMsgDto;
import com.hugo.java.springboot.rabbitmq.java_springboot_rabbitmq.messaging.enums.QueueConstants;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class Consumer {

    ObjectMapper objectMapper;

    @RabbitListener(queues = QueueConstants.TOPIC_ORDER_CREATED_USER_SEND_EMAIL_NOTIFICATION)
    public void orderCreatedUserSendEmailNotificationListener(Message message) {
        try {
            OrderCreatedMsgDto orderCreatedMsgDto = objectMapper.readValue(message.getBody(), OrderCreatedMsgDto.class);
            log.info("Consumer.orderCreatedUserSendEmailNotificationListener - {}", orderCreatedMsgDto.toString());
        } catch (Exception e) {
            log.error("Consumer.orderCreatedUserSendEmailNotificationListener - Error: {}", e.getMessage(), e);
        }
    }

    @RabbitListener(queues = QueueConstants.TOPIC_ORDER_CREATED_FINANCIAL_NOTIFICATION)
    public void orderCreatedFinancialNotificationListener(Message message) {
        try {
            OrderCreatedMsgDto orderCreatedMsgDto = objectMapper.readValue(message.getBody(), OrderCreatedMsgDto.class);
            log.info("Consumer.orderCreatedFinancialNotificationListener - {}", orderCreatedMsgDto.toString());
        } catch (Exception e) {
            log.error("Consumer.orderCreatedFinancialNotificationListener - Error: {}", e.getMessage(), e);
        }
    }

}
