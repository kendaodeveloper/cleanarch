package com.example.cleanarch.usecase.impl.customer;

import com.example.cleanarch.gateway.base.customer.UpdateCustomerGateway;
import com.example.cleanarch.usecase.base.customer.UpdateCustomerUseCase;
import com.example.cleanarch.usecase.base.customer.request.CustomerUseCaseRequest;
import com.example.cleanarch.usecase.base.customer.response.CustomerUseCaseResponse;
import com.example.cleanarch.usecase.impl.customer.mapper.CustomerUseCaseMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class UpdateCustomerUseCaseImpl implements UpdateCustomerUseCase {
  private final UpdateCustomerGateway updateCustomerGateway;

  @Override
  public CustomerUseCaseResponse execute(UUID id, CustomerUseCaseRequest request) {
    return CustomerUseCaseMapper.mapGatewayToUseCase(
        this.updateCustomerGateway.update(id, CustomerUseCaseMapper.mapUseCaseToGateway(request))
    );
  }
}
