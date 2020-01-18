package org.joaogsma.citysuggestion.core.actions;

import java.util.stream.Stream;
import javax.inject.Inject;
import org.joaogsma.citysuggestion.core.models.City;

public class FindCandidateCitiesAction {
  @Inject
  FindCandidateCitiesAction() {}

  public Stream<City> call(String searchTerm) {
    return null;
  }
}
