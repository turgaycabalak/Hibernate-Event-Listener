package com.orderservice.repository;

import java.util.UUID;

import com.orderservice.entity.Customer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {
}
