package com.orderservice.dto.customer;

public record CustomerCreateRequest(
    String firstName,
    String lastName,
    String address
) {
}
