package com.example.cleanarch.entity.exception;

public class UnauthorizedException extends RuntimeException {
  public UnauthorizedException(String details) {
    super(details);
  }
}