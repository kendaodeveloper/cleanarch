package com.example.cleanarch.gateway.api.address.factory;

import com.example.cleanarch.gateway.api.address.abstraction.AddressServiceAbstraction;
import com.example.cleanarch.gateway.base.address.GetAddressByPostalCodeGateway;
import com.example.cleanarch.gateway.base.address.enumerable.AddressGatewayCountry;
import com.example.cleanarch.gateway.base.address.exception.AddressNotFoundException;
import com.example.cleanarch.gateway.base.address.exception.InvalidCountryException;
import com.example.cleanarch.gateway.base.address.exception.UnableToGetAddress;
import com.example.cleanarch.gateway.base.address.response.AddressGatewayResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class AddressGatewayFactory implements GetAddressByPostalCodeGateway {
  private List<AddressServiceAbstraction> services;

  @Override
  public AddressGatewayResponse getAddressByPostalCode(String postalCode, AddressGatewayCountry country)
      throws AddressNotFoundException, UnableToGetAddress, InvalidCountryException {
    return this.services.stream()
        .filter(x -> x.getCountryType().equals(country))
        .findFirst()
        .map(x -> x.getAddressByPostalCode(postalCode))
        .orElseThrow(() -> {
          throw new InvalidCountryException(country);
        });
  }
}
