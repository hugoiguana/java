package com.hugo.java.springboot.rabbitmq.service_producer.exception;


import java.util.UUID;

public class SendCreateOrderMsgException extends AppGenericException {

    public SendCreateOrderMsgException(Exception e, UUID traceId, UUID productId) {
        super(e, String.format("Error when sending orderCreated message. traceId=%s - productId=%s", traceId, productId));
    }
}
