package com.orderservice.dto.customer;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.orderservice.dto.order.OrderResponse;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record CustomerResponse(
    Long id,
    String firstName,
    String lastName,
    String address,

    List<OrderResponse> orders
) {
}
