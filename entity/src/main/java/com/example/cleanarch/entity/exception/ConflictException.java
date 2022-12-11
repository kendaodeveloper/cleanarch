package com.example.cleanarch.entity.exception;

public class ConflictException extends RuntimeException {
  public ConflictException(String details) {
    super(details);
  }
}