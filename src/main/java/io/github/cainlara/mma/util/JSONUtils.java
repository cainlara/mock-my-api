package io.github.cainlara.mma.util;

import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.cainlara.mma.domain.MMARest;

public final class JSONUtils {

  private static final ObjectMapper DEFAULT_OBJECT_MAPPER = new ObjectMapper();

  private JSONUtils() {
    // hide constructor
  }

  public static List<MMARest> getEndPointsFromString(final String fileContent) throws JsonProcessingException {
    if (fileContent != null && !fileContent.trim().isEmpty()) {
      return DEFAULT_OBJECT_MAPPER.readValue(fileContent,
          DEFAULT_OBJECT_MAPPER.getTypeFactory().constructCollectionType(List.class, MMARest.class));
    }

    return Collections.emptyList();
  }
}
