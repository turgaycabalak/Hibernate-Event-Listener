package com.orderservice.repository;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import com.orderservice.entity.Item;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, UUID> {
  List<Item> findByIdIn(Set<UUID> itemIds);
}
