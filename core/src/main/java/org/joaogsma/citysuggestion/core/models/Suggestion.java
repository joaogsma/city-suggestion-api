package org.joaogsma.citysuggestion.core.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = ImmutableSuggestion.class)
public abstract class Suggestion {
  public abstract String name();

  @JsonProperty("latitude")
  public abstract double lat();

  @JsonProperty("longitude")
  public abstract double lng();

  public abstract double score();

  public static Suggestion of(City city, double score) {
    return ImmutableSuggestion.builder()
        .name(city.name())
        .lat(city.lat())
        .lng(city.lng())
        .score(score)
        .build();
  }
}
