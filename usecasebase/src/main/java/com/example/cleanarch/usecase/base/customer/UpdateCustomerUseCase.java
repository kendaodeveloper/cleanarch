package com.example.cleanarch.usecase.base.customer;

import com.example.cleanarch.usecase.base.customer.request.CustomerUseCaseRequest;
import com.example.cleanarch.usecase.base.customer.response.CustomerUseCaseResponse;

import java.util.UUID;

public interface UpdateCustomerUseCase {
  CustomerUseCaseResponse execute(UUID id, CustomerUseCaseRequest request);
}
