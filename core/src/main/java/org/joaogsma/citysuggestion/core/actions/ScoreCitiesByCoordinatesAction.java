package org.joaogsma.citysuggestion.core.actions;

import com.google.common.collect.ImmutableMap;
import com.javadocmd.simplelatlng.LatLng;
import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;
import javax.inject.Inject;
import org.joaogsma.citysuggestion.core.models.City;

public class ScoreCitiesByCoordinatesAction {
  private static final double EARTH_CIRCUMFERENCE_IN_METERS = 40075000.0;

  @Inject
  public ScoreCitiesByCoordinatesAction() {}

  public Map<City, Double> call(Stream<City> cities, Double lat, Double lng) {
    return cities.collect(
        ImmutableMap.toImmutableMap(
            Function.identity(),
            city -> {
              final LatLng cityCoord = new LatLng(city.lat(), city.lng());
              final LatLng queryCoord =
                  new LatLng(lat != null ? lat : city.lat(), lng != null ? lng : city.lng());
              final double distance = LatLngTool.distance(cityCoord, queryCoord, LengthUnit.METER);
              return 1 - 2 * distance / EARTH_CIRCUMFERENCE_IN_METERS;
            }));
  }
}
