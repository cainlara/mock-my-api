package io.github.cainlara.mma.config;

import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Configuration;

import io.github.cainlara.mma.MMAAplication;

@Configuration
public class MMASpringCustomContainer implements WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> {
  public void customize(ConfigurableServletWebServerFactory factory) {
    factory.setPort(MMAAplication.getPort());
  }
}
