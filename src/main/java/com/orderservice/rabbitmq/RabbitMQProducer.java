package com.orderservice.rabbitmq;

import java.util.concurrent.atomic.AtomicInteger;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class RabbitMQProducer {
  private static final AtomicInteger COUNT = new AtomicInteger(1);
  private final RabbitTemplate rabbitTemplate;


  public void sendMessage(String name) {
    if (name == null || name.isBlank()) {
      throw new IllegalArgumentException("RabbitMQ: Name cannot be null or blank");
    }
    String message = COUNT.getAndIncrement() + ". message send: " + "Merhaba " + name;

    try {
      rabbitTemplate.convertAndSend("exchange-name", "routing-key", message);
    } catch (Exception e) {
      log.error("Message sending failed: {}", e.getMessage());
    }
  }

  public void publish(Object payload) {
    rabbitTemplate.convertAndSend("exchange-name", "routing-key", payload);
  }
}
