package com.example.cleanarch.usecase.base.customer;

import com.example.cleanarch.usecase.base.customer.response.CustomerUseCaseResponse;

import java.util.UUID;

public interface GetCustomerByIdUseCase {
  CustomerUseCaseResponse execute(UUID id);
}
