package org.joaogsma.citysuggestion.common.config;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.dropwizard.Configuration;
import org.immutables.value.Value;

@Value.Immutable
@JsonDeserialize(as = ImmutableCommonConfig.class)
public abstract class CommonConfig extends Configuration {
  public abstract String foo();

  public abstract String toString();
}
