package com.orderservice.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@Entity
@SuperBuilder
@NoArgsConstructor
public class Order extends BaseEntity {
  private String orderNumber;
  private String orderName;
  private LocalDateTime orderDate;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "order_item_mapping",
  joinColumns = @JoinColumn(name = "order_id"),
  inverseJoinColumns = @JoinColumn(name = "item_id"))
  private List<Item> items;

  @JsonBackReference
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "customer_id")
  private Customer customer;
}
