package io.github.cainlara.mma.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.cainlara.mma.MMAAplication;
import io.github.cainlara.mma.controller.impl.MMACustomExecutorImpl;
import io.github.cainlara.mma.core.domain.MMARest;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class MMAGenericController {
  private Map<String, Map<String, MMARest>> restsMap = null;
  private IMMACustomExecutor customExecutor = null;

  @PostConstruct
  private void loadProvidedEndpoints() {
    if (ObjectUtils.isNotEmpty(MMAAplication.getRests())) {
      MMAAplication.getRests().forEach(rest -> {

        if (getRestsMap().containsKey(rest.getMethod())) {
          getRestsMap().get(rest.getMethod()).put(rest.getUrl(), rest);
        } else {
          Map<String, MMARest> newRest = new HashMap<>();
          newRest.put(rest.getUrl(), rest);
          getRestsMap().put(rest.getMethod(), newRest);
        }
      });
    }
  }

  @GetMapping(path = "**")
  public ResponseEntity<Object> buildGetResponse(final HttpServletRequest request) {
    String url = request.getRequestURI();

    return handleRequest("GET", url);
  }

  @PostMapping(path = "**")
  public ResponseEntity<Object> buildPostResponse(final HttpServletRequest request) {
    String url = request.getRequestURI();

    return handleRequest("POST", url);
  }

  @PutMapping(path = "**")
  public ResponseEntity<Object> buildPutResponse(final HttpServletRequest request) {
    String url = request.getRequestURI();

    return handleRequest("PUT", url);
  }

  @DeleteMapping(path = "**")
  public ResponseEntity<Object> buildDeleteResponse(final HttpServletRequest request) {
    String url = request.getRequestURI();

    return handleRequest("DELETE", url);
  }

  private ResponseEntity<Object> handleRequest(final String method, final String url) {
    ResponseEntity<Object> response = null;
    
    log.info("Executing " + method + " on " + url);

    if (getRestsMap().containsKey(method) && getRestsMap().get(method).containsKey(url)) {
      MMARest rest = getRestsMap().get(method).get(url);
      response = getCustomExecutor().execute(rest.getResponse(), rest.getResponseStatusCode());
    } else {
      log.warn("Requested combination of method and url was not declared.");
      response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
    
    log.info("Execution ended. Sending response now");

    return response;
  }

  private IMMACustomExecutor getCustomExecutor() {
    if (customExecutor == null) {
      customExecutor = new MMACustomExecutorImpl();
    }

    return customExecutor;
  }

  private Map<String, Map<String, MMARest>> getRestsMap() {
    if (restsMap == null) {
      restsMap = new HashMap<>();
    }

    return restsMap;
  }
}
