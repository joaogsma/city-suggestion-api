package org.joaogsma.citysuggestion.core.command;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.google.common.collect.ImmutableList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.joaogsma.citysuggestion.core.actions.FindCandidateCitiesAction;
import org.joaogsma.citysuggestion.core.actions.MergeScoresAction;
import org.joaogsma.citysuggestion.core.actions.ScoreCitiesByCoordinatesAction;
import org.joaogsma.citysuggestion.core.actions.ScoreCitiesByNameAction;
import org.joaogsma.citysuggestion.core.commands.SuggestCitiesCommand;
import org.joaogsma.citysuggestion.core.fixtures.CoordinateScoresFixture;
import org.joaogsma.citysuggestion.core.fixtures.FinalScoresFixture;
import org.joaogsma.citysuggestion.core.fixtures.InputFixture;
import org.joaogsma.citysuggestion.core.fixtures.NameScoresFixture;
import org.joaogsma.citysuggestion.core.fixtures.SuggestionFixture;
import org.joaogsma.citysuggestion.core.models.City;
import org.joaogsma.citysuggestion.core.models.ImmutableSuggestion;
import org.joaogsma.citysuggestion.core.models.Suggestion;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class SuggestCitiesCommandTests {
  private final String SEARCH_TERM = InputFixture.buildSearchTerm();
  private final double LAT = InputFixture.buildLat();
  private final double LNG = InputFixture.buildLng();
  private final Map<City, Double> FINAL_SCORES = FinalScoresFixture.build();
  private final List<Suggestion> SUGGESTIONS = SuggestionFixture.buildList();
  private Map<City, Double> NAME_SCORES = NameScoresFixture.build();
  private Map<City, Double> COORDINATES_SCORES = CoordinateScoresFixture.build();
  @Mock private List<City> CITIES;
  @Mock private Iterator<City> CITIES_ITERATOR;

  @Mock private FindCandidateCitiesAction findCandidateCitiesAction;
  @Mock private ScoreCitiesByNameAction scoreCitiesByNameAction;
  @Mock private ScoreCitiesByCoordinatesAction scoreCitiesByCoordinatesAction;
  @Mock private MergeScoresAction mergeScoresAction;
  @InjectMocks SuggestCitiesCommand command;

  @Test
  void whenBothLatAndLngArePresent_shouldCallAllActions() {
    when(CITIES.iterator()).thenReturn(CITIES_ITERATOR);
    when(findCandidateCitiesAction.call(SEARCH_TERM)).thenReturn(CITIES);
    when(scoreCitiesByNameAction.call(CITIES_ITERATOR)).thenReturn(NAME_SCORES);
    when(scoreCitiesByCoordinatesAction.call(CITIES_ITERATOR, LAT, LNG))
        .thenReturn(COORDINATES_SCORES);
    when(mergeScoresAction.call(NAME_SCORES, COORDINATES_SCORES)).thenReturn(FINAL_SCORES);

    assertThat(command.call(SEARCH_TERM, LAT, LNG)).isEqualTo(SUGGESTIONS);

    verify(CITIES, times(2)).iterator();
    verify(findCandidateCitiesAction).call(SEARCH_TERM);
    verify(scoreCitiesByNameAction).call(CITIES.iterator());
    verify(scoreCitiesByCoordinatesAction).call(CITIES_ITERATOR, LAT, LNG);
    verify(mergeScoresAction).call(NAME_SCORES, COORDINATES_SCORES);
  }

  @Test
  void whenLngIsMissing_shouldCallAllActions() {
    when(CITIES.iterator()).thenReturn(CITIES_ITERATOR);
    when(findCandidateCitiesAction.call(SEARCH_TERM)).thenReturn(CITIES);
    when(scoreCitiesByNameAction.call(CITIES_ITERATOR)).thenReturn(NAME_SCORES);
    when(scoreCitiesByCoordinatesAction.call(CITIES_ITERATOR, LAT, null))
        .thenReturn(COORDINATES_SCORES);
    when(mergeScoresAction.call(NAME_SCORES, COORDINATES_SCORES)).thenReturn(FINAL_SCORES);

    assertThat(command.call(SEARCH_TERM, LAT, null)).isEqualTo(SUGGESTIONS);

    verify(CITIES, times(2)).iterator();
    verify(findCandidateCitiesAction).call(SEARCH_TERM);
    verify(scoreCitiesByNameAction).call(CITIES.iterator());
    verify(scoreCitiesByCoordinatesAction).call(CITIES_ITERATOR, LAT, null);
    verify(mergeScoresAction).call(NAME_SCORES, COORDINATES_SCORES);
  }

  @Test
  void whenLatIsMissing_shouldCallAllActions() {
    when(CITIES.iterator()).thenReturn(CITIES_ITERATOR);
    when(findCandidateCitiesAction.call(SEARCH_TERM)).thenReturn(CITIES);
    when(scoreCitiesByNameAction.call(CITIES_ITERATOR)).thenReturn(NAME_SCORES);
    when(scoreCitiesByCoordinatesAction.call(CITIES_ITERATOR, null, LNG))
        .thenReturn(COORDINATES_SCORES);
    when(mergeScoresAction.call(NAME_SCORES, COORDINATES_SCORES)).thenReturn(FINAL_SCORES);

    assertThat(command.call(SEARCH_TERM, null, LNG)).isEqualTo(SUGGESTIONS);

    verify(CITIES, times(2)).iterator();
    verify(findCandidateCitiesAction).call(SEARCH_TERM);
    verify(scoreCitiesByNameAction).call(CITIES.iterator());
    verify(scoreCitiesByCoordinatesAction).call(CITIES_ITERATOR, null, LNG);
    verify(mergeScoresAction).call(NAME_SCORES, COORDINATES_SCORES);
  }

  @Test
  void whenBothCoordinatesAreMissing_shouldNotScoreByCoordinates() {
    when(CITIES.iterator()).thenReturn(CITIES_ITERATOR);
    when(findCandidateCitiesAction.call(SEARCH_TERM)).thenReturn(CITIES);
    when(scoreCitiesByNameAction.call(CITIES_ITERATOR)).thenReturn(NAME_SCORES);

    final List<Suggestion> expected =
        NAME_SCORES
            .entrySet()
            .stream()
            .map(entry -> ImmutableSuggestion.of(entry.getKey(), entry.getValue()))
            .collect(ImmutableList.toImmutableList());
    assertThat(command.call(SEARCH_TERM, null, null)).isEqualTo(expected);

    verify(CITIES).iterator();
    verify(findCandidateCitiesAction).call(SEARCH_TERM);
    verify(scoreCitiesByNameAction).call(CITIES_ITERATOR);
    verify(scoreCitiesByCoordinatesAction, never()).call(any(), any(), any());
    verify(mergeScoresAction, never()).call(any(), any());
  }
}
