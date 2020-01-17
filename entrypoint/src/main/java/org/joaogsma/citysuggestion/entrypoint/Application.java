package org.joaogsma.citysuggestion.entrypoint;

import io.dropwizard.setup.Environment;
import org.joaogsma.citysuggestion.common.config.CommonConfig;
import org.joaogsma.citysuggestion.common.config.ImmutableCommonConfig;
import org.joaogsma.citysuggestion.entrypoint.modules.ConfigurationModule;

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

    System.out.println(component.resources().size());

    component.resources().forEach(environment.jersey()::register);
  }
}
