package com.example.cleanarch.gateway.base.customer;

import com.example.cleanarch.gateway.base.customer.dto.CustomerGatewayDto;
import com.example.cleanarch.gateway.base.customer.exception.CustomerNotFoundException;

public interface GetCustomerByEmailGateway {
  CustomerGatewayDto getOneByEmail(String email) throws CustomerNotFoundException;
}
