package org.joaogsma.citysuggestion.core.actions;

import java.util.Map;
import javax.inject.Inject;
import org.joaogsma.citysuggestion.core.models.City;

public class AggregateScoresAction {
  @Inject
  AggregateScoresAction() {}

  public Map<City, Double> call(Map<City, Double> nameScores, Map<City, Double> coordinateScores) {
    return null;
  }
}
