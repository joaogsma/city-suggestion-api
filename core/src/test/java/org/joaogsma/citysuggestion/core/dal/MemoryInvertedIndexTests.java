package org.joaogsma.citysuggestion.core.dal;

import static org.assertj.core.api.Assertions.assertThat;

import com.google.common.base.Predicates;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;
import org.joaogsma.citysuggestion.core.fixtures.CityFixture;
import org.joaogsma.citysuggestion.core.models.City;
import org.junit.jupiter.api.Test;

public class MemoryInvertedIndexTests {
  private final List<City> CITIES = CityFixture.buildList();
  private final Map<City, Set<String>> CITY_TRIGRAMS =
      ImmutableMap.<City, Set<String>>builder()
          .put(
              CITIES.get(0),
              ImmutableSet.of(
                  " on", "  o", "da ", "ond", " lo", "  l", "ana", "on,", "don", "ada", " ca",
                  "can", "n, ", "nad", "  c", "lon", "ndo"))
          .put(
              CITIES.get(1),
              ImmutableSet.of(
                  "oh,", "  o", " us", "h, ", "ond", " lo", " oh", "  l", "  u", "sa ", "don",
                  "n, ", "on,", "usa", "lon", "ndo"))
          .build();

  private MemoryInvertedIndex<String, City> index =
      new MemoryInvertedIndex<>(CITIES.stream(), city -> CITY_TRIGRAMS.get(city).stream());

  @Test
  void shouldContainAllTrigramsAndCities() {
    final Set<String> intersectionTrigrams =
        CITY_TRIGRAMS.values().stream().reduce(Sets::intersection).get();
    intersectionTrigrams
        .stream()
        .map(trigram -> index.get(Stream.of(trigram)))
        .forEach(cities -> assertThat(cities).isEqualTo(CITY_TRIGRAMS.keySet()));

    CITY_TRIGRAMS.forEach(
        (city, trigrams) ->
            trigrams
                .stream()
                .filter(Predicates.not(intersectionTrigrams::contains))
                .map(trigram -> index.get(Stream.of(trigram)))
                .forEach(cities -> assertThat(cities).isEqualTo(ImmutableSet.of(city))));
  }
}
