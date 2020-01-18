package org.joaogsma.citysuggestion.core.actions;

import java.util.Map;
import java.util.stream.Stream;
import javax.inject.Inject;
import org.joaogsma.citysuggestion.core.models.City;

public class ScoreCitiesByNameAction {
  @Inject
  ScoreCitiesByNameAction() {}

  public Map<City, Double> call(Stream<City> cities) {
    return null;
  }
}
