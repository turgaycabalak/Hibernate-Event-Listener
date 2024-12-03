package com.orderservice.dto.orderitem;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.orderservice.dto.order.OrderResponse;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ItemResponse(
    Long id,
    String productName,
    Integer quantity,

    OrderResponse order
) {
}
