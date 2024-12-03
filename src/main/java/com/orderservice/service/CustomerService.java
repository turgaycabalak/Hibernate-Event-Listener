package com.orderservice.service;

import com.orderservice.dto.customer.CustomerCreateRequest;
import com.orderservice.entity.Customer;
import com.orderservice.rabbitmq.RabbitMQProducer;
import com.orderservice.repository.CustomerRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CustomerService {
  private final CustomerRepository customerRepository;
  private final RabbitMQProducer rabbitMQProducer;

  @Transactional
  public Customer save(CustomerCreateRequest request) {
    Customer toSave = Customer.builder()
        .firstName(request.firstName())
        .lastName(request.lastName())
        .address(request.address())
        .build();
    Customer saved = customerRepository.save(toSave);

    //rabbitMQProducer.publish(saved);

    return saved;
  }
}
