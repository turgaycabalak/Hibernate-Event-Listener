package com.orderservice.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;

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
public class Item extends BaseEntity {
  private String productName;
  private Integer quantity;

  @JsonBackReference
  @ManyToMany(mappedBy = "items", fetch = FetchType.LAZY)
  private List<Order> orders;
}
