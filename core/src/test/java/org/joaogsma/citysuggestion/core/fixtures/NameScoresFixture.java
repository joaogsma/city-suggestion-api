package org.joaogsma.citysuggestion.core.fixtures;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Streams;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Map;
import org.joaogsma.citysuggestion.core.models.City;

public class NameScoresFixture {
  private static final Double[] SCORE_VALUES = {0.9, 0.5};
  private static final Map<City, Double> SCORES =
      Streams.zip(
              CityFixture.buildList().stream(),
              Arrays.stream(SCORE_VALUES),
              AbstractMap.SimpleImmutableEntry::new)
          .collect(ImmutableMap.toImmutableMap(Map.Entry::getKey, Map.Entry::getValue));

  public static Map<City, Double> build() {
    return SCORES;
  }
}
