package com.example.cleanarch.gateway.database.customer;

import com.example.cleanarch.gateway.base.customer.dto.CustomerGatewayDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "customer")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerTable implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false, updatable = false)
  private UUID id;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "cpf", nullable = false, unique = true)
  private String cpf;

  @Column(name = "email", nullable = false, unique = true)
  private String email;

  @Column(name = "birth_date", nullable = false)
  private LocalDate birthDate;

  @Column(name = "created_at", nullable = false, updatable = false)
  private LocalDateTime createdAt;

  @Column(name = "updated_at")
  private LocalDateTime updatedAt;

  @PrePersist
  private void setCreatedAt() {
    this.createdAt = LocalDateTime.now();
  }

  @PreUpdate
  private void setUpdatedAt() {
    this.updatedAt = LocalDateTime.now();
  }

  public CustomerGatewayDto toDto() {
    return new CustomerGatewayDto(
        this.id,
        this.name,
        this.cpf,
        this.email,
        this.birthDate
    );
  }
}