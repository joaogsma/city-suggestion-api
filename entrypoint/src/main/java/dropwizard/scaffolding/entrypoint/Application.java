package dropwizard.scaffolding.entrypoint;

import dropwizard.scaffolding.common.config.CommonConfig;
import io.dropwizard.setup.Environment;

public class Application extends io.dropwizard.Application<CommonConfig> {
  public static void main(String[] args) throws Exception {
    new Application().run(args);
  }

  @Override
  public void run(CommonConfig configuration, Environment environment) throws Exception {
    // nothing to do yet
  }
}
