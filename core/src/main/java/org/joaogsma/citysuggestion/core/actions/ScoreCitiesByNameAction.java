package org.joaogsma.citysuggestion.core.actions;

import java.util.Iterator;
import java.util.Map;
import javax.inject.Inject;
import org.joaogsma.citysuggestion.core.models.City;

public class ScoreCitiesByNameAction {
  @Inject
  ScoreCitiesByNameAction() {}

  public Map<City, Double> call(Iterator<City> candidates) {
    return null;
  }
}
