package io.github.cainlara.mma.util;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Properties;

import io.github.cainlara.mma.domain.MMAVersionDescription;

public final class MMAFileUtils {

  private static final String GIT_SOURCE_FILE = "git.properties";

  private MMAFileUtils() {
    // hide constructor
  }

  public static MMAVersionDescription readGitValues() throws IOException {
    MMAVersionDescription gitValues = new MMAVersionDescription();

    InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream(GIT_SOURCE_FILE);

    Properties prop = new Properties();
    prop.load(input);

    String buildTimeStr = getValueOr(prop, "\"git.build.time\"", "unknown");
    String buildVersionStr = getValueOr(prop, "\"git.build.version\"", "unknown");
    String commitIdStr = getValueOr(prop, "\"git.commit.id.abbrev\"", "unknown");

    OffsetDateTime offsetDateTime = OffsetDateTime.parse(buildTimeStr);
    LocalDateTime localDateTime = offsetDateTime.toLocalDateTime();

    gitValues.setBuildTime(localDateTime);
    gitValues.setBuildVersion(buildVersionStr);
    gitValues.setCommitId(commitIdStr);

    return gitValues;
  }

  private static String getValueOr(final Properties source, final String key, final String defaultValue) {
    String value = source.getProperty(key);

    if (value == null || value.isBlank()) {
      return defaultValue == null ? "" : defaultValue.trim();
    }

    return value.replaceAll("[\" ,]+", "").trim();
  }
}
