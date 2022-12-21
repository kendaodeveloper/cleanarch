package com.example.cleanarch.usecase.impl.address;

import com.example.cleanarch.gateway.base.address.GetAddressByPostalCodeGateway;
import com.example.cleanarch.gateway.base.address.enumerable.AddressGatewayCountry;
import com.example.cleanarch.usecase.base.address.GetAddressByPostalCodeUseCase;
import com.example.cleanarch.usecase.base.address.enumerable.AddressUseCaseCountry;
import com.example.cleanarch.usecase.base.address.response.AddressUseCaseResponse;
import com.example.cleanarch.usecase.impl.address.mapper.AddressUseCaseMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetAddressByPostalCodeUseCaseImpl implements GetAddressByPostalCodeUseCase {
  private GetAddressByPostalCodeGateway getAddressByPostalCodeGateway;

  @Override
  public AddressUseCaseResponse getAddressByPostalCode(String postalCode, AddressUseCaseCountry country) {
    return AddressUseCaseMapper.mapGatewayToUseCase(
        this.getAddressByPostalCodeGateway.getAddressByPostalCode(postalCode, AddressGatewayCountry.valueOf(country.toString()))
    );
  }
}
