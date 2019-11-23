package dropwizard.scaffolding.config;

import org.immutables.value.Value;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import io.dropwizard.Configuration;

@Value.Immutable
@JsonDeserialize(as = ImmutableCommonConfiguration.class)
public abstract class CommonConfiguration extends Configuration {
  public abstract String foo();
}
