package io.github.cainlara.mma.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.cainlara.mma.MMAApplication;
import io.github.cainlara.mma.domain.MMARest;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(path="/mma-admin-instance")
@Slf4j
public class MMAInstanceController {

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<MMARest>> getAllRest() {
    log.info("Retreaving all RESTs");
    ResponseEntity<List<MMARest>> response = null;

    try {
      List<MMARest> rests = MMAApplication.getRests();

      if (rests == null || rests.isEmpty()) {
        response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
      } else {
        response = ResponseEntity.status(HttpStatus.OK).body(rests);
      }
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    log.info("All RESTs retrieved");

    return response;
  }
  
}
