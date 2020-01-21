package org.joaogsma.citysuggestion.core.modules;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Stream;
import org.joaogsma.citysuggestion.core.fixtures.TrigramFixture;
import org.joaogsma.citysuggestion.core.models.City;
import org.joaogsma.citysuggestion.core.text.ExtractTrigramsFunction;
import org.junit.jupiter.api.Test;

public class InvertedIndexModuleTests {
  private static final Map<City, Set<String>> CITY_TRIGRAMS = TrigramFixture.buildCItyTrigramMap();

  private final ExtractTrigramsFunction trigramsFn = new TestExtractTrigramsFunction();

  private final InvertedIndexModule module =
      new InvertedIndexModule(InvertedIndexModuleTests.class.getResource("cities.json").getPath());

  @Test
  void shouldProvideAllCitiesOnFile() {
    assertThat(module.provideCityStream()).containsExactlyElementsOf(CITY_TRIGRAMS.keySet());
  }

  @Test
  void shouldProvideKeysFunction() {
    final Function<City, Stream<String>> keysFn = module.provideKeysFunction(trigramsFn);
    CITY_TRIGRAMS.forEach(
        (city, trigrams) -> {
          assertThat(keysFn.apply(city)).containsExactlyElementsOf(trigrams);
        });
  }

  private static class TestExtractTrigramsFunction extends ExtractTrigramsFunction {
    @Override
    public Set<String> apply(String str) {
      return CITY_TRIGRAMS
          .entrySet()
          .stream()
          .filter(entry -> entry.getKey().name().equals(str))
          .findFirst()
          .map(Map.Entry::getValue)
          .get();
    }
  }
}
