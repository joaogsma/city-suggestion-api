package org.joaogsma.citysuggestion.core.actions;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Set;
import java.util.stream.Stream;
import org.joaogsma.citysuggestion.core.dal.InvertedIndexFacade;
import org.joaogsma.citysuggestion.core.fixtures.InputFixture;
import org.joaogsma.citysuggestion.core.models.City;
import org.joaogsma.citysuggestion.core.text.ExtractTrigramsFunction;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class FindCandidateCitiesActionTests {
  private String SEARCH_TERM = InputFixture.buildSearchTerm();
  @Mock private Set<String> TRIGRAMS;
  @Mock private Stream<String> TRIGRAM_STREAM;
  @Mock private Set<City> CITIES;
  @Mock private Stream<City> CITIES_STREAM;

  @Mock private ExtractTrigramsFunction extractTrigramsFn;
  @Mock private InvertedIndexFacade facade;
  @InjectMocks private FindCandidateCitiesAction action;

  @Test
  void shouldCallTheFacade() {
    when(extractTrigramsFn.apply(SEARCH_TERM)).thenReturn(TRIGRAMS);
    when(TRIGRAMS.stream()).thenReturn(TRIGRAM_STREAM);
    when(facade.get(TRIGRAM_STREAM)).thenReturn(CITIES);
    when(CITIES.stream()).thenReturn(CITIES_STREAM);

    assertThat(action.call(SEARCH_TERM)).isEqualTo(CITIES_STREAM);

    verify(extractTrigramsFn).apply(SEARCH_TERM);
    verify(TRIGRAMS).stream();
    verify(facade).get(TRIGRAM_STREAM);
    verify(CITIES).stream();
  }
}
