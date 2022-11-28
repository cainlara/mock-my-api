package io.github.cainlara.mma.controller.impl;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import io.github.cainlara.mma.controller.IMMACustomExecutor;

public class MMACustomExecutorImpl implements IMMACustomExecutor {

  @Override
  public ResponseEntity<Object> execute(final Map<String, Object> response, int responseStatusCode) {
    return ResponseEntity.status(responseStatusCode).body(response);
  }

}
