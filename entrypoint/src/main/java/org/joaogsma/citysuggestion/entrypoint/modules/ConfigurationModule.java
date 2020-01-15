package org.joaogsma.citysuggestion.entrypoint.modules;

import dagger.Module;
import dagger.Provides;
import org.joaogsma.citysuggestion.common.config.CommonConfig;

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
