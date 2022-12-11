package com.example.cleanarch.gateway.base.address.exception;

import com.example.cleanarch.entity.exception.BadRequestException;

public class InvalidAddressPostalCodeException extends BadRequestException {
  public InvalidAddressPostalCodeException(String postalCode) {
    super(String.format("Postal Code %s is invalid", postalCode));
  }
}
