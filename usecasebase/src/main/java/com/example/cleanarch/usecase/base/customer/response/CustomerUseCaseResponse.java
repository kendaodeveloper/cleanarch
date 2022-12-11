package com.example.cleanarch.usecase.base.customer.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerUseCaseResponse {
  private UUID id;
  private String name;
  private String cpf;
  private String email;
  private LocalDate birthDate;
}
