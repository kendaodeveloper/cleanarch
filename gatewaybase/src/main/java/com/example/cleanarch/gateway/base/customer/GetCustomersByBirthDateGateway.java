package com.example.cleanarch.gateway.base.customer;

import com.example.cleanarch.gateway.base.customer.dto.CustomerGatewayDto;

import java.time.LocalDate;
import java.util.List;

public interface GetCustomersByBirthDateGateway {
  List<CustomerGatewayDto> getAllByBirthDate(LocalDate birthDate, Integer pageNumber, Integer pageSize);
}
