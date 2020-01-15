package org.joaogsma.citysuggestion.core.commands;

import java.util.List;
import javax.inject.Inject;
import org.joaogsma.citysuggestion.core.models.Suggestion;

public class SuggestCitiesCommand {
  @Inject
  SuggestCitiesCommand() {}

  public List<Suggestion> call(String searchTerm, Double lat, Double lng) {
    return null;
  }
}
