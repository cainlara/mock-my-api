package io.github.cainlara.mma.domain;

import java.util.Map;

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
public class MMARest {
  private String method;
  private String url;
  private int responseStatusCode;
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  private Map<String, Object> response;
  
}
