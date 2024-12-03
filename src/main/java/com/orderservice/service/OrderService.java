package com.orderservice.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import com.orderservice.dto.order.OrderCreateRequest;
import com.orderservice.entity.Customer;
import com.orderservice.entity.Item;
import com.orderservice.entity.Order;
import com.orderservice.repository.CustomerRepository;
import com.orderservice.repository.ItemRepository;
import com.orderservice.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderService {
  private final OrderRepository orderRepository;
  private final CustomerRepository customerRepository;
  private final ItemRepository orderItemRepository;

  @Transactional
  public Order save(OrderCreateRequest request) {
    Customer customer = customerRepository.findById(request.customerId())
        .orElseThrow(() -> new IllegalStateException("Customer not found by id: " + request.customerId()));

    List<Item> items = orderItemRepository.findByIdIn(request.orderItems());

    int number = ThreadLocalRandom.current().nextInt(1000, 10000);
    String orderNumber = customer.getLastName() + "-" + number;
    Order toSave = Order.builder()
        .orderNumber(orderNumber)
        .orderName(request.orderName())
        .orderDate(LocalDateTime.now())
        .items(items)
        .customer(customer)
        .build();

    return orderRepository.save(toSave);
  }
}
