package com.example.cleanarch.entrypoint.api.configuration.swagger;

import com.example.cleanarch.entity.enumerable.Environment;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import lombok.extern.log4j.Log4j2;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Log4j2
@Configuration
public class SwaggerConfiguration {
  private final Environment env;
  private final String dns;
  private final ApplicationContext context;

  public SwaggerConfiguration(
      @Value("${application.env}") Environment env,
      @Value("${application.dns}") String dns,
      ApplicationContext context
  ) {
    this.env = env;
    this.dns = dns;
    this.context = context;
  }

  @Bean
  public GroupedOpenApi getGroupedOpenApi() {
    return GroupedOpenApi.builder()
        .group("default")
        .pathsToMatch(!this.env.equals(Environment.PRODUCTION) ? "/**" : "")
        .build();
  }

  @Bean
  public OpenAPI getOpenAPI() {
    OpenAPI openAPI = new OpenAPI()
        .info(
            new Info()
                .title("Clean Arch")
                .description(
                    "Clean Arch project demonstration" +
                        (this.env.equals(Environment.PRODUCTION) ? "<br/>Swagger is unavailable" : "")
                )
                .version(this.getVersion(this.context))
        );

    if (!this.env.equals(Environment.DEVELOPMENT)) {
      openAPI.setServers(List.of(
          new Server().url("https://" + this.dns).description("Default server in HTTPS"),
          new Server().url("http://" + this.dns).description("Default server in HTTP")
      ));
    }

    return openAPI;
  }

  private String getVersion(ApplicationContext context) {
    try {
      final var beans =
          context == null ? null : context.getBeansWithAnnotation(SpringBootApplication.class);
      if (beans != null && !beans.isEmpty()) {
        return beans.values().toArray()[0].getClass().getPackage().getImplementationVersion();
      }
    } catch (Throwable t) {
      log.error("Unable to get application version: " + t);
    }

    return null;
  }
}