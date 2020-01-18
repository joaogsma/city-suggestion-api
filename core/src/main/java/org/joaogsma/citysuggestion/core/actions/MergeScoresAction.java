package org.joaogsma.citysuggestion.core.actions;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import javax.inject.Inject;
import org.joaogsma.citysuggestion.core.models.City;

public class MergeScoresAction {
  @Inject
  MergeScoresAction() {}

  /** Computes the final score as the mean of both scores */
  public Map<City, Double> call(Map<City, Double> nameScores, Map<City, Double> coordinateScores) {
    Preconditions.checkArgument(
        nameScores.keySet().equals(coordinateScores.keySet()),
        String.format(
            "Expecting key sets %s and %s to be equal, but they were not",
            setToString(nameScores.keySet()), setToString(coordinateScores.keySet())));

    return nameScores
        .entrySet()
        .stream()
        .collect(
            ImmutableMap.toImmutableMap(
                Map.Entry::getKey,
                entry -> mergeScores(entry.getValue(), coordinateScores.get(entry.getKey()))));
  }

  private <T> String setToString(Set<T> set) {
    return set.stream().map(T::toString).collect(Collectors.joining(", "));
  }

  private double mergeScores(double nameScore, double coordinateScore) {
    return (nameScore + coordinateScore) / 2;
  }
}
