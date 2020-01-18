package org.joaogsma.citysuggestion.core.dal;

import java.util.function.Function;
import java.util.stream.Stream;
import javax.inject.Inject;
import org.joaogsma.citysuggestion.core.models.City;

public class InvertedIndexFacade {
  @Inject
  InvertedIndexFacade() {}

  public void add(City city, Function<String, Stream<String>> keysFunction) {}

  public Stream<City> get(Stream<String> keys) {
    return null;
  }
}
