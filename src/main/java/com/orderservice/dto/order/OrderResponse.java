package com.orderservice.dto.order;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.orderservice.dto.customer.CustomerResponse;
import com.orderservice.dto.orderitem.ItemResponse;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record OrderResponse(
    Long id,
    String orderNumber,
    String orderName,
    LocalDateTime orderDate,

    List<ItemResponse> items,
    CustomerResponse customer
) {
}
