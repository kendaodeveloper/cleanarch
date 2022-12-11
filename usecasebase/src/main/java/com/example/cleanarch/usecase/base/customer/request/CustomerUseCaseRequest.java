package com.example.cleanarch.usecase.base.customer.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerUseCaseRequest {
  private String name;
  private String cpf;
  private String email;
  private LocalDate birthDate;
}
