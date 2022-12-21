package com.example.cleanarch.entrypoint.api.customer.mapper;

import com.example.cleanarch.entrypoint.api.customer.request.WriteCustomerEndpointRequest;
import com.example.cleanarch.entrypoint.api.customer.response.CustomerEndpointResponse;
import com.example.cleanarch.usecase.base.customer.request.CustomerUseCaseRequest;
import com.example.cleanarch.usecase.base.customer.response.CustomerUseCaseResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CustomerEndpointMapper {
  public static CustomerEndpointResponse mapUseCaseToEndpoint(CustomerUseCaseResponse customerUseCase) {
    return new CustomerEndpointResponse(
        customerUseCase.getId(),
        customerUseCase.getName(),
        customerUseCase.getCpf(),
        customerUseCase.getEmail(),
        customerUseCase.getBirthDate()
    );
  }

  public static CustomerUseCaseRequest mapEndpointToUseCase(WriteCustomerEndpointRequest customerEndpoint) {
    return new CustomerUseCaseRequest(
        customerEndpoint.getName(),
        customerEndpoint.getCpf(),
        customerEndpoint.getEmail(),
        customerEndpoint.getBirthDate()
    );
  }
}
