package io.github.cainlara.mma.config;

import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.server.servlet.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Configuration;

import io.github.cainlara.mma.MMAApplication;

@Configuration
public class MMASpringCustomContainer implements WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> {

  @Override
  public void customize(ConfigurableServletWebServerFactory factory) {
    factory.setPort(MMAApplication.getPort());
  }
  
}
