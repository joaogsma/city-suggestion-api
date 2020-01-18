package org.joaogsma.citysuggestion.core.fixtures;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Streams;
import java.util.Arrays;
import java.util.List;
import org.joaogsma.citysuggestion.core.models.ImmutableSuggestion;
import org.joaogsma.citysuggestion.core.models.Suggestion;

public class SuggestionFixture {
  private static final Double[] SCORE_VALUES = {0.9499749305159372, 0.740259022223247};
  private static final List<Suggestion> SUGGESTIONS =
      Streams.zip(
              CityFixture.buildList().stream(),
              Arrays.stream(SCORE_VALUES),
              ImmutableSuggestion::of)
          .collect(ImmutableList.toImmutableList());

  public static Suggestion build() {
    return SUGGESTIONS.get(0);
  }

  public static List<Suggestion> buildList() {
    return SUGGESTIONS;
  }
}
