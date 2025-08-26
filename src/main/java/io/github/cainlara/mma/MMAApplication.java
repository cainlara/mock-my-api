package io.github.cainlara.mma;

import java.util.List;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cainlara.arguments.manager.core.AMUtility;
import io.github.cainlara.arguments.manager.domain.AMArgument;
import io.github.cainlara.mma.core.MMAException;
import io.github.cainlara.mma.core.MMAInputTransformer;
import io.github.cainlara.mma.domain.MMARest;

@SpringBootApplication
public class MMAApplication {

  private static List<MMARest> rests = null;
  private static Integer port = null;

  public static void main(String[] args) throws MMAException {
    AMArgument sourceArgument = AMUtility.getInstance().getArgumentByName("source", args);
    AMArgument portArgument = AMUtility.getInstance().getArgumentByName("port", args);

    if (sourceArgument == null) {
      throw new MMAException("No data valid source path provided");
    }

    if (ObjectUtils.isNotEmpty(portArgument)) {
      if (isPortValueValid(portArgument.getArgumentValue())) {
        port = Integer.valueOf(portArgument.getArgumentValue());
      } else {
        throw new MMAException("Port value (" + portArgument.getArgumentValue() +") is not valid. It MUST be a number");
      }
    } else {
      port = 8080;
    }

    rests = MMAInputTransformer.of(sourceArgument.getArgumentValue()).parse();

    SpringApplication.run(MMAApplication.class, args);
  }

  private static boolean isPortValueValid(final String portValue) {
    return ObjectUtils.isNotEmpty(portValue) && portValue.matches("^[0-9]+");
  }

  public static List<MMARest> getRests() {
    return rests;
  }

  public static Integer getPort() {
    return port;
  }
  
}
