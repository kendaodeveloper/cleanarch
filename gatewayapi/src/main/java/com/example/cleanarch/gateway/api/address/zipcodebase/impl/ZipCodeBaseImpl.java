package com.example.cleanarch.gateway.api.address.zipcodebase.impl;

import com.example.cleanarch.gateway.api.address.abstraction.AddressServiceAbstraction;
import com.example.cleanarch.gateway.api.address.zipcodebase.client.ZipCodeBaseClient;
import com.example.cleanarch.gateway.api.address.zipcodebase.response.ZipCodeBaseAddressResponse;
import com.example.cleanarch.gateway.base.address.enumerable.AddressGatewayCountry;
import com.example.cleanarch.gateway.base.address.exception.AddressNotFoundException;
import com.example.cleanarch.gateway.base.address.exception.UnableToGetAddress;
import com.example.cleanarch.gateway.base.address.response.AddressGatewayResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ZipCodeBaseImpl extends AddressServiceAbstraction {
  private ZipCodeBaseClient zipCodeBaseClient;

  @Override
  public AddressGatewayResponse getAddressByPostalCode(String postalCode) throws AddressNotFoundException, UnableToGetAddress {
    final Optional<ZipCodeBaseAddressResponse> response;

    try {
      response = this.zipCodeBaseClient.getAddressByPostalCode(postalCode);
    } catch (Throwable t) {
      throw new UnableToGetAddress(String.format(
          "Unable to get US address by postal code %s -> error: %s", postalCode, t
      ));
    }

    return response
        .map(address -> {
          if (!address.foundAddress()) {
            throw new AddressNotFoundException(postalCode);
          }

          return address.toGatewayResponse(postalCode);
        }).orElseThrow(() -> new UnableToGetAddress(String.format(
            "Unable to get US address by postal code %s -> response is null", postalCode
        )));
  }

  @Override
  public AddressGatewayCountry getCountryType() {
    return AddressGatewayCountry.US;
  }
}
