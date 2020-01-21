package org.joaogsma.citysuggestion.entrypoint.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.List;
import org.immutables.value.Value;
import org.joaogsma.citysuggestion.core.models.Suggestion;

@Value.Immutable
@JsonSerialize(as = ImmutableSuggestionsResponse.class)
public interface SuggestionsResponse {
  List<Suggestion> suggestions();
}
