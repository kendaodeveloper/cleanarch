package com.example.cleanarch.entity.exception;

public class UnprocessableEntityException extends RuntimeException {
  public UnprocessableEntityException(String details) {
    super(details);
  }
}