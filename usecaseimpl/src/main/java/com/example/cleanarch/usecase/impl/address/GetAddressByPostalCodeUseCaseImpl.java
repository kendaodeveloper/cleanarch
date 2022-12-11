package com.example.cleanarch.usecase.impl.address;

import com.example.cleanarch.gateway.base.address.GetAddressByPostalCodeGateway;
import com.example.cleanarch.gateway.base.address.enumerable.AddressGatewayCountry;
import com.example.cleanarch.gateway.base.address.response.AddressGatewayResponse;
import com.example.cleanarch.usecase.base.address.GetAddressByPostalCodeUseCase;
import com.example.cleanarch.usecase.base.address.enumerable.AddressUseCaseCountry;
import com.example.cleanarch.usecase.base.address.response.AddressUseCaseResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetAddressByPostalCodeUseCaseImpl implements GetAddressByPostalCodeUseCase {
  private GetAddressByPostalCodeGateway getAddressByPostalCodeGateway;

  @Override
  public AddressUseCaseResponse getAddressByPostalCode(String postalCode, AddressUseCaseCountry country) {
    return this.mapGatewayResponseToUseCaseResponse(
        this.getAddressByPostalCodeGateway.getAddressByPostalCode(postalCode, AddressGatewayCountry.valueOf(country.toString()))
    );
  }

  private AddressUseCaseResponse mapGatewayResponseToUseCaseResponse(AddressGatewayResponse request) {
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
