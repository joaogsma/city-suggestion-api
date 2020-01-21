package org.joaogsma.citysuggestion.core.fixtures;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Streams;
import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.joaogsma.citysuggestion.core.models.City;

public class TrigramFixture {
  private static final Set<String> SEARCH_TERM_TRIGRAMS =
      ImmutableSet.of("  l", " lo", "lon", "ond", "nd ");
  private static final List<Set<String>> CITY_TRIGRAM_LIST =
      ImmutableList.of(
          ImmutableSet.of(
              " on", "  o", "da ", "ond", " lo", "  l", "ana", "on,", "don", "ada", " ca", "can",
              "n, ", "nad", "  c", "lon", "ndo"),
          ImmutableSet.of(
              "oh,", "  o", " us", "h, ", "ond", " lo", " oh", "  l", "  u", "sa ", "don", "n, ",
              "on,", "usa", "lon", "ndo"));

  private static final Map<City, Set<String>> CITY_TRIGRAM_MAP =
      Streams.zip(
              CityFixture.buildList().stream(),
              buildCityTrigramList().stream(),
              AbstractMap.SimpleImmutableEntry::new)
          .collect(ImmutableMap.toImmutableMap(Map.Entry::getKey, Map.Entry::getValue));

  public static Set<String> buildSearchTermTrigrams() {
    return SEARCH_TERM_TRIGRAMS;
  }

  public static List<Set<String>> buildCityTrigramList() {
    return CITY_TRIGRAM_LIST;
  }

  public static Map<City, Set<String>> buildCItyTrigramMap() {
    return CITY_TRIGRAM_MAP;
  }
}
