package io.github.cainlara.mma.core;

public class MMAException extends Exception {
  private static final long serialVersionUID = 1032672370443279612L;

  public MMAException(final String message) {
    super(message);
  }

  public MMAException(final String message, final Throwable cause) {
    super(message, cause);
  }
}
