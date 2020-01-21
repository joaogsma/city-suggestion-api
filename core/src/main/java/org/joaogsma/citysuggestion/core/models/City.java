package org.joaogsma.citysuggestion.core.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.immutables.value.Value;

@Value.Immutable
@JsonDeserialize(as = ImmutableCity.class)
public interface City {
  String name();

  @JsonProperty("latitude")
  double lat();

  @JsonProperty("longitude")
  double lng();
}
