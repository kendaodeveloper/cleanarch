package com.example.cleanarch.gateway.base.customer;

import com.example.cleanarch.gateway.base.customer.dto.CustomerGatewayDto;

import java.util.List;

public interface GetAllCustomersGateway {
  List<CustomerGatewayDto> getAll(Integer pageNumber, Integer pageSize);
}
