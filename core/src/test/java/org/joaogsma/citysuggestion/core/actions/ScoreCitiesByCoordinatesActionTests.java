package org.joaogsma.citysuggestion.core.actions;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import org.joaogsma.citysuggestion.core.fixtures.CityFixture;
import org.joaogsma.citysuggestion.core.fixtures.CoordinateScoresFixture;
import org.joaogsma.citysuggestion.core.fixtures.InputFixture;
import org.joaogsma.citysuggestion.core.models.City;
import org.junit.jupiter.api.Test;

public class ScoreCitiesByCoordinatesActionTests {
  private final double LAT = InputFixture.buildLat();
  private final double LNG = InputFixture.buildLng();
  private final List<City> CITIES = CityFixture.buildList();
  private final Map<City, Double> COORDINATE_SCORES = CoordinateScoresFixture.build();

  private final ScoreCitiesByCoordinatesAction action = new ScoreCitiesByCoordinatesAction();

  @Test
  void shouldComputeTheScores() {
    final Comparator<Double> closeEnough = (a, b) -> Math.abs(a - b) <= 1e-6 ? 0 : 1;
    assertThat(action.call(CITIES.iterator(), LAT, LNG))
        .usingComparatorForType(closeEnough, Double.class)
        .isEqualToComparingFieldByField(COORDINATE_SCORES);
  }
}
