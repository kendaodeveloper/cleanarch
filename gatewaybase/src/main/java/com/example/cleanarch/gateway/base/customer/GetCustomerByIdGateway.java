package com.example.cleanarch.gateway.base.customer;

import com.example.cleanarch.gateway.base.customer.dto.CustomerGatewayDto;
import com.example.cleanarch.gateway.base.customer.exception.CustomerNotFoundException;

import java.util.UUID;

public interface GetCustomerByIdGateway {
  CustomerGatewayDto getOneById(UUID id) throws CustomerNotFoundException;
}
