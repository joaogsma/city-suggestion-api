package org.joaogsma.citysuggestion.entrypoint.models;

import java.util.List;
import org.immutables.value.Value;
import org.joaogsma.citysuggestion.core.models.Suggestion;

@Value.Immutable
public interface SuggestionsResponse {
  List<Suggestion> suggestions();
}
