package com.hugo.java.springboot.rabbitmq.service_producer.exception;


public class AppGenericException extends RuntimeException {

    public AppGenericException(Exception e, String msg) {
        super(msg, e);
    }
}
