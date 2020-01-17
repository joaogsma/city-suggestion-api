package org.joaogsma.citysuggestion.core.commands;

import com.google.common.collect.ImmutableList;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import org.joaogsma.citysuggestion.core.actions.AggregateScoresAction;
import org.joaogsma.citysuggestion.core.actions.FindCandidateCitiesAction;
import org.joaogsma.citysuggestion.core.actions.ScoreCitiesByCoordinatesAction;
import org.joaogsma.citysuggestion.core.actions.ScoreCitiesByNameAction;
import org.joaogsma.citysuggestion.core.models.City;
import org.joaogsma.citysuggestion.core.models.ImmutableSuggestion;
import org.joaogsma.citysuggestion.core.models.Suggestion;

public class SuggestCitiesCommand {
  private final FindCandidateCitiesAction findCandidateCitiesAction;
  private final ScoreCitiesByNameAction scoreCitiesByNameAction;
  private final ScoreCitiesByCoordinatesAction scoreCitiesByCoordinatesAction;
  private final AggregateScoresAction aggregateScoresAction;

  @Inject
  SuggestCitiesCommand(
      FindCandidateCitiesAction findCandidateCitiesAction,
      ScoreCitiesByNameAction scoreCitiesByNameAction,
      ScoreCitiesByCoordinatesAction scoreCitiesByCoordinatesAction,
      AggregateScoresAction aggregateScoresAction) {
    this.findCandidateCitiesAction = findCandidateCitiesAction;
    this.scoreCitiesByNameAction = scoreCitiesByNameAction;
    this.scoreCitiesByCoordinatesAction = scoreCitiesByCoordinatesAction;
    this.aggregateScoresAction = aggregateScoresAction;
  }

  public List<Suggestion> call(String searchTerm, Double lat, Double lng) {
    final List<City> cities = findCandidateCitiesAction.call(searchTerm);
    final Map<City, Double> nameScores = scoreCitiesByNameAction.call(cities.iterator());

    if (lat == null && lng == null) {
      return nameScores
          .entrySet()
          .stream()
          .map(entry -> ImmutableSuggestion.of(entry.getKey(), entry.getValue()))
          .collect(ImmutableList.toImmutableList());
    }

    final Map<City, Double> coordinateScores =
        scoreCitiesByCoordinatesAction.call(cities.iterator(), lat, lng);
    final Map<City, Double> finalScores = aggregateScoresAction.call(nameScores, coordinateScores);
    return finalScores
        .entrySet()
        .stream()
        .map(entry -> ImmutableSuggestion.of(entry.getKey(), entry.getValue()))
        .collect(ImmutableList.toImmutableList());
  }
}
