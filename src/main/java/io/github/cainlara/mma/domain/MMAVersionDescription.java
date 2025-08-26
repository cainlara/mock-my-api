package io.github.cainlara.mma.domain;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class MMAVersionDescription {

  private LocalDateTime buildTime;
  private String buildVersion;
  private String commitId;

  public static MMAVersionDescription emptyVersionDescription() {
    return new MMAVersionDescription(LocalDateTime.now(), "unknown", "unknown");
  }
}
