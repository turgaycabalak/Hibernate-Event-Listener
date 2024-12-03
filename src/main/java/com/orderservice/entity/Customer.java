package com.orderservice.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@Entity
@SuperBuilder
@NoArgsConstructor
public class Customer extends BaseEntity {
  private String firstName;
  private String lastName;
  private String address;

  @JsonManagedReference
  @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
  private List<Order> orders;
}
