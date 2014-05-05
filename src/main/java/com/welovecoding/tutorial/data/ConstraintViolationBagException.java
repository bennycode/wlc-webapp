package com.welovecoding.exception;

import java.util.HashSet;
import java.util.Set;
import javax.validation.ConstraintViolation;

public class ConstraintViolationBagException extends Exception {

  private final Set<ConstraintViolation<?>> constraintViolations;

  public ConstraintViolationBagException(String message, Set<? extends ConstraintViolation<?>> constraintViolations) {
    super(message);
    this.constraintViolations = new HashSet<>(constraintViolations);
  }

  public ConstraintViolationBagException(Set<? extends ConstraintViolation<?>> constraintViolations) {
    this.constraintViolations = new HashSet<>(constraintViolations);
  }

  public Set<ConstraintViolation<?>> getConstraintViolations() {
    return constraintViolations;
  }
}
