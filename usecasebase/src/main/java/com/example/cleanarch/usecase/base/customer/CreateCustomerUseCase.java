package com.example.cleanarch.usecase.base.customer;

import com.example.cleanarch.usecase.base.customer.request.CustomerUseCaseRequest;
import com.example.cleanarch.usecase.base.customer.response.CustomerUseCaseResponse;

public interface CreateCustomerUseCase {
  CustomerUseCaseResponse execute(CustomerUseCaseRequest request);
}
