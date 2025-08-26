package io.github.cainlara.mma.executor.impl;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import io.github.cainlara.mma.executor.IMMACustomExecutor;

public class MMACustomExecutorImpl implements IMMACustomExecutor {

  @Override
  public ResponseEntity<Object> execute(Map<String, Object> response, int responseStatusCode) {
    return ResponseEntity.status(responseStatusCode).body(response);
  }
}
