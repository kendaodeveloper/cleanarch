package com.example.cleanarch.entity.exception;

public class BadRequestException extends RuntimeException {
  public BadRequestException(String details) {
    super(details);
  }
}