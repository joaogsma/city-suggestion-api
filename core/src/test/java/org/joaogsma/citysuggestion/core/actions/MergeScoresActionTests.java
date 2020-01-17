package org.joaogsma.citysuggestion.core.actions;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import com.google.common.collect.ImmutableMap;
import java.util.Map;
import org.joaogsma.citysuggestion.core.fixtures.CoordinateScoresFixture;
import org.joaogsma.citysuggestion.core.fixtures.FinalScoresFixture;
import org.joaogsma.citysuggestion.core.fixtures.NameScoresFixture;
import org.joaogsma.citysuggestion.core.models.City;
import org.junit.jupiter.api.Test;

public class MergeScoresActionTests {
  private final Map<City, Double> NAME_SCORES = NameScoresFixture.build();
  private final Map<City, Double> COORDINATE_SCORES = CoordinateScoresFixture.build();
  private final Map<City, Double> FINAL_SCORES = FinalScoresFixture.build();

  private final MergeScoresAction action = new MergeScoresAction();

  @Test
  void shouldComputeTheMeanOfTheScores() {
    assertThat(action.call(NAME_SCORES, COORDINATE_SCORES)).isEqualTo(FINAL_SCORES);
  }

  @Test
  void whenTheKeySetsAreNotEqual_shouldThrowException() {
    final Map<City, Double> smallNameScores =
        ImmutableMap.<City, Double>builder().put(NAME_SCORES.entrySet().iterator().next()).build();
    final Map<City, Double> smallCoordinateScores =
        ImmutableMap.<City, Double>builder()
            .put(COORDINATE_SCORES.entrySet().iterator().next())
            .build();

    assertThatThrownBy(() -> action.call(smallNameScores, COORDINATE_SCORES))
        .isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(() -> action.call(NAME_SCORES, smallCoordinateScores))
        .isInstanceOf(IllegalArgumentException.class);
  }
}
