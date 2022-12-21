package com.example.cleanarch.entrypoint.api.address.mapper;

import com.example.cleanarch.entrypoint.api.address.enumerable.AddressEndpointCountry;
import com.example.cleanarch.entrypoint.api.address.response.AddressEndpointResponse;
import com.example.cleanarch.usecase.base.address.response.AddressUseCaseResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AddressEndpointMapper {
  public static AddressEndpointResponse mapUseCaseToEndpoint(AddressUseCaseResponse addressUseCase) {
    return new AddressEndpointResponse(
        addressUseCase.getPostalCode(),
        addressUseCase.getPublicPlace(),
        addressUseCase.getNumber(),
        addressUseCase.getNeighbourhood(),
        addressUseCase.getComplement(),
        addressUseCase.getCity(),
        addressUseCase.getState(),
        AddressEndpointCountry.valueOf(addressUseCase.getCountry().toString())
    );
  }
}
