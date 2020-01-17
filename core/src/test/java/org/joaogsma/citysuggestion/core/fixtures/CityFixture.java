package org.joaogsma.citysuggestion.core.fixtures;

import com.google.common.collect.ImmutableList;
import java.util.List;
import org.joaogsma.citysuggestion.core.models.City;
import org.joaogsma.citysuggestion.core.models.ImmutableCity;

public class CityFixture {
  private static final List<City> CITIES =
      ImmutableList.of(
          ImmutableCity.builder().name("London, ON, Canada").lat(42.98339).lng(-81.23304).build(),
          ImmutableCity.builder().name("London, OH, USA").lat(39.88645).lng(-83.44825).build());

  public static City build() {
    return CITIES.get(0);
  }

  public static List<City> buildList() {
    return CITIES;
  }
}
