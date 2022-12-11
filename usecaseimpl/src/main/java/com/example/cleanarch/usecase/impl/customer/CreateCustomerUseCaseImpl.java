package com.example.cleanarch.usecase.impl.customer;

import com.example.cleanarch.gateway.base.customer.CreateCustomerGateway;
import com.example.cleanarch.usecase.base.customer.CreateCustomerUseCase;
import com.example.cleanarch.usecase.base.customer.request.CustomerUseCaseRequest;
import com.example.cleanarch.usecase.base.customer.response.CustomerUseCaseResponse;
import com.example.cleanarch.usecase.impl.customer.mapper.CustomerUseCaseMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateCustomerUseCaseImpl implements CreateCustomerUseCase {
  private final CreateCustomerGateway createCustomerGateway;

  @Override
  public CustomerUseCaseResponse execute(CustomerUseCaseRequest request) {
    return CustomerUseCaseMapper.mapGatewayToUseCase(
        this.createCustomerGateway.create(
            CustomerUseCaseMapper.mapUseCaseToGateway(request)
        )
    );
  }
}
