package com.example.cleanarch.gateway.base.customer;

import com.example.cleanarch.gateway.base.customer.exception.CustomerNotFoundException;

import java.util.UUID;

public interface DeleteCustomerByIdGateway {
  void deleteOneById(UUID id) throws CustomerNotFoundException;
}
