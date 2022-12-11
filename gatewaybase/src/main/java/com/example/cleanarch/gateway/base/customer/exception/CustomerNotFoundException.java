package com.example.cleanarch.gateway.base.customer.exception;

import com.example.cleanarch.entity.exception.NotFoundException;

public class CustomerNotFoundException extends NotFoundException {
  public CustomerNotFoundException() {
    super("Customer not found");
  }
}
