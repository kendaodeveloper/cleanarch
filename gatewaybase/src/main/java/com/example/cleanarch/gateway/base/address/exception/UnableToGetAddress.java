package com.example.cleanarch.gateway.base.address.exception;

import com.example.cleanarch.entity.exception.BadGatewayException;

public class UnableToGetAddress extends BadGatewayException {
  public UnableToGetAddress(String message) {
    super(message);
  }
}
