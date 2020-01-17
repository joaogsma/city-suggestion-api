package org.joaogsma.citysuggestion.core.fixtures;

public class InputFixture {
  private static final String SEARCH_TERM = "lond";
  private static final double LAT = 42.984755;
  private static final double LNG = -81.245249;

  public static String buildSearchTerm() {
    return SEARCH_TERM;
  }

  public static double buildLat() {
    return LAT;
  }

  public static double buildLng() {
    return LNG;
  }
}
