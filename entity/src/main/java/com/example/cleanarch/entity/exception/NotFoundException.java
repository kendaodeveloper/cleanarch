package com.example.cleanarch.entity.exception;

public class NotFoundException extends RuntimeException {
  public NotFoundException(String details) {
    super(details);
  }
}