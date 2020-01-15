package org.joaogsma.citysuggestion.entrypoint.resources;

import com.google.common.base.Predicates;
import java.util.Optional;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path(HelloWorldResource.PATH)
@Produces(MediaType.TEXT_PLAIN)
public class HelloWorldResource {
  static final String PATH = "/hello-world";

  private static final Logger LOGGER = LoggerFactory.getLogger(HelloWorldResource.class);

  private static final String DEFAULT_NAME = "stranger";
  private static final String TEMPLATE = "Hello, %s!";

  @Inject
  HelloWorldResource() {}

  @GET
  public String call(@QueryParam("name") Optional<String> name) {
    LOGGER.info("Received a request with name {}", name);
    return String.format(
        TEMPLATE, name.filter(Predicates.not(String::isEmpty)).orElse(DEFAULT_NAME));
  }
}
