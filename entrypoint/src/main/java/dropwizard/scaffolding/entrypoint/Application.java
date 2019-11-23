package dropwizard.scaffolding.entrypoint;

import dropwizard.scaffolding.common.config.CommonConfig;
import dropwizard.scaffolding.common.config.ImmutableCommonConfig;
import dropwizard.scaffolding.entrypoint.modules.ConfigurationModule;
import io.dropwizard.setup.Environment;

public class Application extends io.dropwizard.Application<CommonConfig> {
  public static void main(String[] args) throws Exception {
    new Application().run(args);
  }

  @Override
  public void run(CommonConfig configuration, Environment environment) throws Exception {
    ApplicationComponent component =
        DaggerApplicationComponent.builder()
            .configurationModule(
                new ConfigurationModule(ImmutableCommonConfig.builder().foo("xalala").build()))
            .build();

    System.out.println("HERE");

    environment.jersey().register(component.helloWorldResource());
  }
}
