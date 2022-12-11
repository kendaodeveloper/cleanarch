package com.example.cleanarch.usecase.impl.customer.mapper;

import com.example.cleanarch.gateway.base.customer.dto.CustomerGatewayDto;
import com.example.cleanarch.usecase.base.customer.request.CustomerUseCaseRequest;
import com.example.cleanarch.usecase.base.customer.response.CustomerUseCaseResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CustomerUseCaseMapper {
  public static CustomerGatewayDto mapUseCaseToGateway(CustomerUseCaseRequest useCase) {
    return new CustomerGatewayDto(
        null,
        useCase.getName(),
        useCase.getCpf(),
        useCase.getEmail(),
        useCase.getBirthDate()
    );
  }

  public static CustomerUseCaseResponse mapGatewayToUseCase(CustomerGatewayDto gateway) {
    return new CustomerUseCaseResponse(
        gateway.getId(),
        gateway.getName(),
        gateway.getCpf(),
        gateway.getEmail(),
        gateway.getBirthDate()
    );
  }
}
