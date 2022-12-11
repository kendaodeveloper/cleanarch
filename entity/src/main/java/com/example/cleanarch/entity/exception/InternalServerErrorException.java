package com.example.cleanarch.entity.exception;

public class InternalServerErrorException extends RuntimeException {
  public InternalServerErrorException(String details) {
    super(details);
  }
}