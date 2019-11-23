package dropwizard.scaffolding.entrypoint.modules;

import dagger.Module;
import dagger.Provides;
import dropwizard.scaffolding.common.config.CommonConfig;

@Module
public class ConfigurationModule {
  private final CommonConfig config;

  public ConfigurationModule(CommonConfig config) {
    this.config = config;
  }

  @Provides
  CommonConfig provide() {
    return config;
  }
}
