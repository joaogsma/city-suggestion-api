package org.joaogsma.citysuggestion.entrypoint.fixtures;

import org.joaogsma.citysuggestion.entrypoint.models.ImmutableSuggestionsResponse;
import org.joaogsma.citysuggestion.entrypoint.models.SuggestionsResponse;

public class SuggestionsResponseFixture {
  public static SuggestionsResponse build() {
    return ImmutableSuggestionsResponse.builder()
        .suggestions(SuggestionFixture.buildList())
        .build();
  }
}
