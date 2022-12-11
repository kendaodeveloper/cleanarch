package com.example.cleanarch.gateway.api.address.viacep.impl;

import com.example.cleanarch.gateway.api.address.abstraction.AddressServiceAbstraction;
import com.example.cleanarch.gateway.api.address.viacep.client.ViaCepClient;
import com.example.cleanarch.gateway.api.address.viacep.response.ViaCepAddressResponse;
import com.example.cleanarch.gateway.base.address.enumerable.AddressGatewayCountry;
import com.example.cleanarch.gateway.base.address.exception.AddressNotFoundException;
import com.example.cleanarch.gateway.base.address.exception.InvalidAddressPostalCodeException;
import com.example.cleanarch.gateway.base.address.exception.UnableToGetAddress;
import com.example.cleanarch.gateway.base.address.response.AddressGatewayResponse;
import feign.FeignException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ViaCepImpl extends AddressServiceAbstraction {
  private ViaCepClient viaCepClient;

  @Override
  public AddressGatewayResponse getAddressByPostalCode(String postalCode) throws AddressNotFoundException, UnableToGetAddress {
    final Optional<ViaCepAddressResponse> response;

    try {
      response = this.viaCepClient.getAddressByPostalCode(postalCode);
    } catch (FeignException e) {
      switch (HttpStatus.valueOf(e.status())) {
        case BAD_REQUEST:
          throw new InvalidAddressPostalCodeException(postalCode);
        default:
          throw new UnableToGetAddress(String.format(
              "Unable to get BR address by postal code %s -> status: %d", postalCode, e.status()
          ));
      }
    } catch (Throwable t) {
      throw new UnableToGetAddress(String.format(
          "Unable to get BR address by postal code %s -> error: %s", postalCode, t
      ));
    }

    return response
        .map(address -> {
          if (address.containsError()) {
            throw new AddressNotFoundException(postalCode);
          }

          return address.toGatewayResponse();
        }).orElseThrow(() -> new UnableToGetAddress(String.format(
            "Unable to get BR address by postal code %s -> response is null", postalCode
        )));
  }

  @Override
  public AddressGatewayCountry getCountryType() {
    return AddressGatewayCountry.BR;
  }
}
