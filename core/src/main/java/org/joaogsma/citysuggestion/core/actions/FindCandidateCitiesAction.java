package org.joaogsma.citysuggestion.core.actions;

import java.util.Set;
import java.util.stream.Stream;
import javax.inject.Inject;
import org.joaogsma.citysuggestion.core.dal.InvertedIndexFacade;
import org.joaogsma.citysuggestion.core.models.City;
import org.joaogsma.citysuggestion.core.text.ExtractTrigramsFunction;

public class FindCandidateCitiesAction {
  private final InvertedIndexFacade facade;
  private final ExtractTrigramsFunction extractTrigramsFn;

  @Inject
  FindCandidateCitiesAction(InvertedIndexFacade facade, ExtractTrigramsFunction extractTrigramsFn) {
    this.facade = facade;
    this.extractTrigramsFn = extractTrigramsFn;
  }

  public Stream<City> call(String searchTerm) {
    final Set<String> trigrams = extractTrigramsFn.apply(searchTerm);
    return facade.get(trigrams.stream());
  }
}
