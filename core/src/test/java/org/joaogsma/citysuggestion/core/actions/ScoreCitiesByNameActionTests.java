package org.joaogsma.citysuggestion.core.actions;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.atMostOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.google.common.collect.ImmutableMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Stream;
import org.joaogsma.citysuggestion.core.fixtures.CityFixture;
import org.joaogsma.citysuggestion.core.fixtures.InputFixture;
import org.joaogsma.citysuggestion.core.fixtures.NameScoresFixture;
import org.joaogsma.citysuggestion.core.models.City;
import org.joaogsma.citysuggestion.core.text.ExtractTrigramsFunction;
import org.joaogsma.citysuggestion.core.text.TrigramSimilarityFunction;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ScoreCitiesByNameActionTests {
  private final String SEARCH_TERM = InputFixture.buildSearchTerm();
  @Mock private Set<String> SEARCH_TERM_TRIGRAMS;
  private final List<City> CITIES = CityFixture.buildList();
  private final Map<City, Set<String>> CITY_TRIGRAMS =
      CITIES
          .stream()
          .collect(
              ImmutableMap.toImmutableMap(
                  Function.identity(), city -> (Set<String>) mock(Set.class)));
  private final Map<City, Double> NAME_SCORES = NameScoresFixture.build();

  @Mock private ExtractTrigramsFunction extractTrigramsFn;
  @Mock private TrigramSimilarityFunction trigramSimilarityFn;
  @InjectMocks private ScoreCitiesByNameAction action;

  @Test
  void whenInputIsEmpty_shouldReturnAnEmptyMap() {
    when(extractTrigramsFn.apply(SEARCH_TERM)).thenReturn(SEARCH_TERM_TRIGRAMS);
    assertThat(action.call(Stream.empty(), SEARCH_TERM)).isEmpty();
    verify(extractTrigramsFn, atMostOnce()).apply(SEARCH_TERM);
  }

  @Test
  void shouldCallTrigramSimilarity() {
    when(extractTrigramsFn.apply(SEARCH_TERM)).thenReturn(SEARCH_TERM_TRIGRAMS);
    CITIES.forEach(
        city -> {
          final Set<String> trigrams = CITY_TRIGRAMS.get(city);
          final Double score = NAME_SCORES.get(city);
          when(extractTrigramsFn.apply(city.name())).thenReturn(trigrams);
          when(trigramSimilarityFn.apply(trigrams, SEARCH_TERM_TRIGRAMS)).thenReturn(score);
        });

    assertThat(action.call(CITIES.stream(), SEARCH_TERM)).isEqualTo(NAME_SCORES);

    verify(extractTrigramsFn).apply(SEARCH_TERM);
    CITIES.forEach(
        city -> {
          verify(extractTrigramsFn).apply(city.name());
          verify(trigramSimilarityFn).apply(CITY_TRIGRAMS.get(city), SEARCH_TERM_TRIGRAMS);
        });
  }
}
