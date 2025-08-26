package io.github.cainlara.mma.core;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ObjectUtils;

import io.github.cainlara.mma.domain.MMARest;
import io.github.cainlara.mma.util.JSONUtils;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(staticName = "of")
public class MMAInputTransformer {

  private static final String VALID_URL_REGEX = "^\\/[a-zA-Z0-9\\/]+$";
  @NonNull
  private String sourcePath;

  public List<MMARest> parse() throws MMAException {
    List<MMARest> rests = null;
    File sourceFile = new File(this.sourcePath);

    if (!sourceFile.exists()) {
      throw new IllegalArgumentException(sourceFile + " file does not exist!");
    }

    try {
      rests = JSONUtils.getEndPointsFromString(getFileContent());
    } catch (Exception e) {
      throw new MMAException("Source file content could not be mapped", e.getCause());
    }

    if (ObjectUtils.isEmpty(rests)) {
      throw new MMAException("Source file seems to be empty");
    }

    String errors = validateRestFromSourceFile(rests);

    if (ObjectUtils.isNotEmpty(errors)) {
      throw new MMAException("Source file seems to contain errors: " + errors);
    }

    return rests;
  }

  private String getFileContent() throws IOException {
    return Files.readString(Path.of(this.sourcePath));
  }

  private String validateRestFromSourceFile(final List<MMARest> rests) {
    final StringBuilder errorsBuilder = new StringBuilder();
    final Map<Integer, MMARest> existingRests = new HashMap<>();

    rests.forEach(rest -> {
      EMMARestMethod method = EMMARestMethod.valueOfAlias(rest.getMethod());

      if (method.equals(EMMARestMethod.UNKNOWN)) {
        errorsBuilder.append(", Unknown Verb " + rest.getMethod());
      }

      if (!rest.getUrl().matches(VALID_URL_REGEX)) {
        errorsBuilder
            .append(", Invalid URL: " + rest.getUrl() + "(Must start with \"/\", no symbols nor blanks allowed)");
      }
      
      if (rest.getResponseStatusCode() <100 || rest.getResponseStatusCode() > 600) {
        errorsBuilder.append(", Unexpected response status code " + rest.getResponseStatusCode());
      }

      if (existingRests.containsKey(rest.hashCode())) {
        errorsBuilder.append(", REST " + rest.toString() + " is declared multiple times");
      } else {
        existingRests.put(rest.hashCode(), rest);
      }
    });

    String errors = errorsBuilder.toString();

    return ObjectUtils.isEmpty(errors) ? errors : errors.substring(1).trim();
  }
}
