package dropwizard.scaffolding;

import dropwizard.scaffolding.config.CommonConfiguration;
import io.dropwizard.setup.Environment;

public class Application extends io.dropwizard.Application<CommonConfiguration> {
  public static void main(String[] args) throws Exception {
    new Application().run(args);
  }

  @Override
  public void run(CommonConfiguration configuration, Environment environment) throws Exception {
    // nothing to do yet
  }
}
