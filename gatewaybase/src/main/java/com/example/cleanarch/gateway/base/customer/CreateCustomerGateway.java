package com.example.cleanarch.gateway.base.customer;

import com.example.cleanarch.gateway.base.customer.dto.CustomerGatewayDto;
import com.example.cleanarch.gateway.base.customer.exception.CustomerAlreadyExistsException;

public interface CreateCustomerGateway {
  CustomerGatewayDto create(CustomerGatewayDto request) throws CustomerAlreadyExistsException;
}
