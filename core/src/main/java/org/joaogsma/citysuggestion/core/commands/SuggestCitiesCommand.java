package org.joaogsma.citysuggestion.core.commands;

import com.google.common.collect.ImmutableList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import org.joaogsma.citysuggestion.core.actions.FindCandidateCitiesAction;
import org.joaogsma.citysuggestion.core.actions.MergeScoresAction;
import org.joaogsma.citysuggestion.core.actions.ScoreCitiesByCoordinatesAction;
import org.joaogsma.citysuggestion.core.actions.ScoreCitiesByNameAction;
import org.joaogsma.citysuggestion.core.models.City;
import org.joaogsma.citysuggestion.core.models.ImmutableSuggestion;
import org.joaogsma.citysuggestion.core.models.Suggestion;

public class SuggestCitiesCommand {
  private final int DEFAULT_LIMIT = 10;
  private final FindCandidateCitiesAction findCandidateCitiesAction;
  private final ScoreCitiesByNameAction scoreCitiesByNameAction;
  private final ScoreCitiesByCoordinatesAction scoreCitiesByCoordinatesAction;
  private final MergeScoresAction mergeScoresAction;

  @Inject
  public SuggestCitiesCommand(
      FindCandidateCitiesAction findCandidateCitiesAction,
      ScoreCitiesByNameAction scoreCitiesByNameAction,
      ScoreCitiesByCoordinatesAction scoreCitiesByCoordinatesAction,
      MergeScoresAction mergeScoresAction) {
    this.findCandidateCitiesAction = findCandidateCitiesAction;
    this.scoreCitiesByNameAction = scoreCitiesByNameAction;
    this.scoreCitiesByCoordinatesAction = scoreCitiesByCoordinatesAction;
    this.mergeScoresAction = mergeScoresAction;
  }

  public List<Suggestion> call(
      String searchTerm, Double lat, Double lng, Integer offset, Integer limit) {
    final List<City> cities =
        findCandidateCitiesAction.call(searchTerm).collect(ImmutableList.toImmutableList());
    final Map<City, Double> finalScores = computeScores(cities, searchTerm, lat, lng);
    return finalScores
        .entrySet()
        .stream()
        .map(entry -> ImmutableSuggestion.of(entry.getKey(), entry.getValue()))
        .sorted(Comparator.comparingDouble(Suggestion::score).reversed())
        .skip(offset != null ? offset : 0)
        .limit(limit != null ? limit : DEFAULT_LIMIT)
        .collect(ImmutableList.toImmutableList());
  }

  private Map<City, Double> computeScores(
      List<City> cities, String searchTerm, Double lat, Double lng) {
    final Map<City, Double> nameScores = scoreCitiesByNameAction.call(cities.stream(), searchTerm);
    if (lat == null && lng == null) {
      return nameScores;
    }
    final Map<City, Double> coordinateScores =
        scoreCitiesByCoordinatesAction.call(cities.stream(), lat, lng);
    return mergeScoresAction.call(nameScores, coordinateScores);
  }
}
