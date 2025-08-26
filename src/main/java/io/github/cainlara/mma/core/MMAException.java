package io.github.cainlara.mma.core;

public class MMAException extends Exception {

  public MMAException(final String message) {
    super(message);
  }

  public MMAException(final String message, final Throwable cause) {
    super(message, cause);
  }
  
}
