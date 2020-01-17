package org.joaogsma.citysuggestion.core.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = ImmutableSuggestion.class)
public interface Suggestion {
  String name();

  @JsonProperty("latitude")
  double lat();

  @JsonProperty("longitude")
  double lng();

  double score();
}
