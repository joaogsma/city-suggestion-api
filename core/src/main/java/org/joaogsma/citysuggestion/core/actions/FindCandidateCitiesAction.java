package org.joaogsma.citysuggestion.core.actions;

import java.util.List;
import javax.inject.Inject;
import org.joaogsma.citysuggestion.core.models.City;

public class FindCandidateCitiesAction {
  @Inject
  FindCandidateCitiesAction() {}

  public List<City> call(String searchTerm) {
    return null;
  }
}
