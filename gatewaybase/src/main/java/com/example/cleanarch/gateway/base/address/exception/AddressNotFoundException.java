package com.example.cleanarch.gateway.base.address.exception;

import com.example.cleanarch.entity.exception.NotFoundException;

public class AddressNotFoundException extends NotFoundException {
  public AddressNotFoundException(String postalCode) {
    super(String.format("Postal Code %s is not found", postalCode));
  }
}
