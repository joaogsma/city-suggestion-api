package org.joaogsma.citysuggestion.core.dal;

import java.util.Set;
import java.util.stream.Stream;
import javax.inject.Inject;
import org.joaogsma.citysuggestion.core.models.City;

public class InvertedIndexFacade {
  private final InvertedIndex<String, City> index;

  @Inject
  public InvertedIndexFacade(InvertedIndex<String, City> index) {
    this.index = index;
  }

  public Set<City> get(Stream<String> keys) {
    return index.get(keys);
  }
}
