package com.example.cleanarch.usecase.impl.address.mapper;

import com.example.cleanarch.gateway.base.address.response.AddressGatewayResponse;
import com.example.cleanarch.usecase.base.address.enumerable.AddressUseCaseCountry;
import com.example.cleanarch.usecase.base.address.response.AddressUseCaseResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AddressUseCaseMapper {
  public static AddressUseCaseResponse mapGatewayToUseCase(AddressGatewayResponse request) {
    return new AddressUseCaseResponse(
        request.getPostalCode(),
        request.getPublicPlace(),
        null,
        request.getNeighbourhood(),
        request.getComplement(),
        request.getCity(),
        request.getState(),
        AddressUseCaseCountry.valueOf(request.getCountry().toString())
    );
  }
}
