package org.joaogsma.citysuggestion.core.actions;

import com.google.common.collect.ImmutableMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Stream;
import javax.inject.Inject;
import org.joaogsma.citysuggestion.core.models.City;
import org.joaogsma.citysuggestion.core.text.ExtractTrigramsFunction;
import org.joaogsma.citysuggestion.core.text.TrigramSimilarityFunction;

public class ScoreCitiesByNameAction {
  private final ExtractTrigramsFunction trigramsOf;
  private final TrigramSimilarityFunction similarityFn;

  @Inject
  ScoreCitiesByNameAction(
      ExtractTrigramsFunction extractTrigramsFn, TrigramSimilarityFunction trigramSimilarityFn) {
    trigramsOf = extractTrigramsFn;
    similarityFn = trigramSimilarityFn;
  }

  public Map<City, Double> call(Stream<City> cities, String searchTerm) {
    final Set<String> queryTrigrams = trigramsOf.apply(searchTerm);
    return cities.collect(
        ImmutableMap.toImmutableMap(
            Function.identity(),
            city -> similarityFn.apply(trigramsOf.apply(city.name()), queryTrigrams)));
  }
}
