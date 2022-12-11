package com.example.cleanarch.usecase.base.customer;

import java.util.UUID;

public interface DeleteCustomerByIdUseCase {
  void execute(UUID id);
}
