package org.joaogsma.citysuggestion.entrypoint.fixtures;

import com.google.common.collect.ImmutableList;
import java.util.List;
import org.joaogsma.citysuggestion.core.models.ImmutableSuggestion;
import org.joaogsma.citysuggestion.core.models.Suggestion;

public class SuggestionFixture {
  private static final List<Suggestion> SUGGESTIONS =
      ImmutableList.of(
          ImmutableSuggestion.builder()
              .name("London, ON, Canada")
              .lat(42.98339)
              .lng(-81.23304)
              .score(0.9)
              .build(),
          ImmutableSuggestion.builder()
              .name("London, OH, USA")
              .lat(39.88645)
              .lng(-83.44825)
              .score(0.5)
              .build());

  public static Suggestion build() {
    return SUGGESTIONS.get(0);
  }

  public static List<Suggestion> buildList() {
    return SUGGESTIONS;
  }
}
