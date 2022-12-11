package com.example.cleanarch.gateway.base.customer;

import com.example.cleanarch.gateway.base.customer.dto.CustomerGatewayDto;
import com.example.cleanarch.gateway.base.customer.exception.CustomerAlreadyExistsException;
import com.example.cleanarch.gateway.base.customer.exception.CustomerNotFoundException;

import java.util.UUID;

public interface UpdateCustomerGateway {
  CustomerGatewayDto update(UUID id, CustomerGatewayDto request) throws CustomerNotFoundException, CustomerAlreadyExistsException;
}
