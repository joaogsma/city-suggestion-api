package org.joaogsma.citysuggestion.core.text;

import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;
import java.util.Set;
import java.util.function.BiFunction;
import javax.inject.Inject;

public class TrigramSimilarityFunction implements BiFunction<Set<String>, Set<String>, Double> {
  @Inject
  public TrigramSimilarityFunction() {}

  @Override
  public Double apply(Set<String> trigrams1, Set<String> trigrams2) {
    Preconditions.checkArgument(
        !trigrams1.isEmpty() || !trigrams2.isEmpty(), "At least one trigram set must be non-empty");
    return (double) Sets.intersection(trigrams1, trigrams2).size()
        / Sets.union(trigrams1, trigrams2).size();
  }
}
