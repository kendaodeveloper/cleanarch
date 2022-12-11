package com.example.cleanarch.gateway.base.customer.exception;

import com.example.cleanarch.entity.exception.ConflictException;

public class CustomerAlreadyExistsException extends ConflictException {
  public CustomerAlreadyExistsException(String field) {
    super(String.format("Customer already exists by field %s", field));
  }
}
