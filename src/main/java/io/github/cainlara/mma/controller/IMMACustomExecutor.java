package io.github.cainlara.mma.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;

public interface IMMACustomExecutor {
  
  ResponseEntity<Object> execute(Map<String, Object> response, int responseStatusCode);

}
