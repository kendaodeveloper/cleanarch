package com.example.cleanarch.gateway.base.customer;

import com.example.cleanarch.gateway.base.customer.dto.CustomerGatewayDto;

import java.util.List;

public interface GetCustomersByNameGateway {
  List<CustomerGatewayDto> getAllByName(String name, Integer pageNumber, Integer pageSize);
}
