package com.example.cleanarch.gateway.base.address.exception;

import com.example.cleanarch.entity.exception.BadRequestException;
import com.example.cleanarch.gateway.base.address.enumerable.AddressGatewayCountry;

public class InvalidCountryException extends BadRequestException {
  public InvalidCountryException(AddressGatewayCountry country) {
    super(String.format("Country %s is invalid", country.toString()));
  }
}
