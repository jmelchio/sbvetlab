package net.physiodelic.sbvetlab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class SbvetlabApplication extends SpringBootServletInitializer {

  public static void main(String[] args) {
    SpringApplication.run(SbvetlabApplication.class, args);
  }

  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
    return builder.sources(SbvetlabApplication.class);
  }
}
