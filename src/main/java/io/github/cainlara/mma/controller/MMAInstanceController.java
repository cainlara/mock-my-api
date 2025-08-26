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
import io.github.cainlara.mma.domain.MMAVersionDescription;
import io.github.cainlara.mma.service.IMMAConfigSrv;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(path="/mma-admin-instance")
@Slf4j
public class MMAInstanceController {

  private final IMMAConfigSrv configService;

  public MMAInstanceController(final IMMAConfigSrv configSrv) {
    this.configService = configSrv;
  }

  @GetMapping(path = "/source", produces = MediaType.APPLICATION_JSON_VALUE)
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

  @GetMapping(path = "/version", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<MMAVersionDescription> getVersionDetails() {
    log.info("Retreaving version details");
    ResponseEntity<MMAVersionDescription> response = null;
    MMAVersionDescription version = null;

    try {
      version = configService.getVersionDescription();

      if (version == null) {
        response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
      } else {
        response = ResponseEntity.status(HttpStatus.OK).body(version);
      }
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      version = MMAVersionDescription.emptyVersionDescription();
      response = ResponseEntity.status(HttpStatus.OK).body(version);
    }

    log.info("Version details {} retrieved", version);

    return response;
  }
  
}
