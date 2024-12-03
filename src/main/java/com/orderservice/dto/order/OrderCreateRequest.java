package com.orderservice.dto.order;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

public record OrderCreateRequest(
    UUID customerId,
    String orderName,
    Set<UUID> orderItems
) {
}
