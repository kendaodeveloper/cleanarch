package com.example.cleanarch.entity.exception;

public class BadGatewayException extends RuntimeException {
  public BadGatewayException(String details) {
    super(details);
  }
}