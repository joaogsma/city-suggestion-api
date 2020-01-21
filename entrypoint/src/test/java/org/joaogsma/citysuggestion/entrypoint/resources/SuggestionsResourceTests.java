package org.joaogsma.citysuggestion.entrypoint.resources;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import org.joaogsma.citysuggestion.core.commands.SuggestCitiesCommand;
import org.joaogsma.citysuggestion.core.models.Suggestion;
import org.joaogsma.citysuggestion.entrypoint.fixtures.SuggestionFixture;
import org.joaogsma.citysuggestion.entrypoint.fixtures.SuggestionsResponseFixture;
import org.joaogsma.citysuggestion.entrypoint.models.SuggestionsResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SuggestionsResourceTests {
  private static final String SEARCH_TERM = "lon";
  private static final double LAT = 42.98339;
  private static final double LNG = -81.23304;
  private static final int OFFSET = 0;
  private static final int LIMIT = 20;
  private static final List<Suggestion> SUGGESTIONS = SuggestionFixture.buildList();
  private static final SuggestionsResponse SUGGESTIONS_RESPONSE =
      SuggestionsResponseFixture.build();

  @Mock private SuggestCitiesCommand command;
  @InjectMocks private SuggestionsResource resource;

  @Test
  void getShouldCallCommand() {
    when(command.call(SEARCH_TERM, LAT, LNG, OFFSET, LIMIT)).thenReturn(SUGGESTIONS);
    assertThat(resource.get(SEARCH_TERM, LAT, LNG, OFFSET, LIMIT)).isEqualTo(SUGGESTIONS_RESPONSE);
    verify(command).call(SEARCH_TERM, LAT, LNG, OFFSET, LIMIT);
  }
}
