package com.orderservice.controller;

import com.orderservice.dto.customer.CustomerCreateRequest;
import com.orderservice.rabbitmq.RabbitMQProducer;
import com.orderservice.service.CustomerService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customers")
public class CustomerController {
  private final RabbitMQProducer rabbitMQProducer;
  private final CustomerService customerService;


  @GetMapping
  public void amqpTest(@RequestParam("name") String name) {
    rabbitMQProducer.sendMessage(name);
  }

  @PostMapping
  public void save(@RequestBody CustomerCreateRequest request) {
    customerService.save(request);
  }
}
