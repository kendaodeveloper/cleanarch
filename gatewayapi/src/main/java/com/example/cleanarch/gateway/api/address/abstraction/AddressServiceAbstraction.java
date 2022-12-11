package com.example.cleanarch.gateway.api.address.abstraction;

import com.example.cleanarch.gateway.base.address.enumerable.AddressGatewayCountry;
import com.example.cleanarch.gateway.base.address.exception.AddressNotFoundException;
import com.example.cleanarch.gateway.base.address.exception.UnableToGetAddress;
import com.example.cleanarch.gateway.base.address.response.AddressGatewayResponse;

public abstract class AddressServiceAbstraction {
  public abstract AddressGatewayResponse getAddressByPostalCode(String postalCode)
      throws AddressNotFoundException, UnableToGetAddress;

  public abstract AddressGatewayCountry getCountryType();
}
