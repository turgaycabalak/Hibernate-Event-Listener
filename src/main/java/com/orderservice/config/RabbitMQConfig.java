package com.orderservice.config;

import java.text.SimpleDateFormat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
  @Bean
  public Queue queue() {
    return new Queue("queue-name", false);
  }

  @Bean
  public Exchange exchange() {
    return new DirectExchange("exchange-name");
  }

  @Bean
  public Binding binding(Queue queue, Exchange exchange) {
    return BindingBuilder.bind(queue)
        .to(exchange)
        .with("routing-key")
        .noargs();
  }

  /**
   * Returns an instance of Jackson2JsonMessageConverter.
   */
  @Bean
  public Jackson2JsonMessageConverter jsonMessageConverter() {
    var objectMapper = new ObjectMapper();
    objectMapper.findAndRegisterModules();

    // LocalDateTime için özel format belirliyoruz
    objectMapper.registerModule(new JavaTimeModule());
    objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

    // LocalDateTime'ı istediğimiz formata çeviriyoruz
    objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss"));

    return new Jackson2JsonMessageConverter(objectMapper);
  }
}
