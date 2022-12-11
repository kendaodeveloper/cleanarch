package com.example.cleanarch.usecase.base.customer;

import com.example.cleanarch.usecase.base.customer.response.CustomerUseCaseResponse;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface GetCustomersByFilterUseCase {
  List<CustomerUseCaseResponse> execute(UUID id, String name, String cpf, String email, LocalDate birthDate, Integer pageNumber, Integer pageSize);
}
