package com.orderservice.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@MappedSuperclass
@NoArgsConstructor
public abstract class BaseEntity {
  @Id
  @Column(name = "id", length = 32, columnDefinition = "UUID default gen_random_uuid()")
  private UUID id;

  @Column(name = "last_updated", nullable = false, columnDefinition = "timestamp default current_timestamp")
  private LocalDateTime lastUpdated;

  @Column(name = "created_date", nullable = false, columnDefinition = "timestamp default current_timestamp")
  private LocalDateTime createdDate;

  @Column(name = "active_status", nullable = false, columnDefinition = "boolean default true")
  private Boolean activeStatus;

  @PrePersist
  public void prePersist() {
    if (this.id == null) {
      this.id = UUID.randomUUID();
    }
    if (createdDate == null) {
      this.createdDate = LocalDateTime.now();
    }
    if (lastUpdated == null) {
      this.lastUpdated = LocalDateTime.now();
    }
    if (activeStatus == null) {
      this.activeStatus = true;
    }
  }

  @PreUpdate
  public void preUpdate() {
    this.lastUpdated = LocalDateTime.now();
  }
}
