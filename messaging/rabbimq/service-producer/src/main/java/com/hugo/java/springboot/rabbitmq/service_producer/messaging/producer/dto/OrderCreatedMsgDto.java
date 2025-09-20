package com.hugo.java.springboot.rabbitmq.service_producer.messaging.producer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreatedMsgDto {

    private UUID traceId;
    private UUID productId;
    private int quantity;

}
