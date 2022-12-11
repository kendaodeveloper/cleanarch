package com.example.cleanarch.gateway.base.address;

import com.example.cleanarch.gateway.base.address.enumerable.AddressGatewayCountry;
import com.example.cleanarch.gateway.base.address.exception.AddressNotFoundException;
import com.example.cleanarch.gateway.base.address.exception.InvalidCountryException;
import com.example.cleanarch.gateway.base.address.exception.UnableToGetAddress;
import com.example.cleanarch.gateway.base.address.response.AddressGatewayResponse;

public interface GetAddressByPostalCodeGateway {
  AddressGatewayResponse getAddressByPostalCode(String postalCode, AddressGatewayCountry country) throws AddressNotFoundException, UnableToGetAddress, InvalidCountryException;
}
