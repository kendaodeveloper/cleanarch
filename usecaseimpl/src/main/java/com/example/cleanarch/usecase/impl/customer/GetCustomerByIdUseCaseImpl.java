package com.example.cleanarch.usecase.impl.customer;

import com.example.cleanarch.gateway.base.customer.GetCustomerByIdGateway;
import com.example.cleanarch.usecase.base.customer.GetCustomerByIdUseCase;
import com.example.cleanarch.usecase.base.customer.response.CustomerUseCaseResponse;
import com.example.cleanarch.usecase.impl.customer.mapper.CustomerUseCaseMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class GetCustomerByIdUseCaseImpl implements GetCustomerByIdUseCase {
  private final GetCustomerByIdGateway getCustomerByIdGateway;

  @Override
  public CustomerUseCaseResponse execute(UUID id) {
    return CustomerUseCaseMapper.mapGatewayToUseCase(
        this.getCustomerByIdGateway.getOneById(id)
    );
  }
}
