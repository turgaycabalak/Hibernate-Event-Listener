package com.orderservice.controller;

import com.orderservice.dto.order.OrderCreateRequest;
import com.orderservice.service.OrderService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {
  private final OrderService orderService;

  @PostMapping
  public void save(@RequestBody OrderCreateRequest request) {
    orderService.save(request);
  }
}
