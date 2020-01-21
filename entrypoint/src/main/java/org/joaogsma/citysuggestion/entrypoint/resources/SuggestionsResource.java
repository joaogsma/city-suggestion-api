package org.joaogsma.citysuggestion.entrypoint.resources;

import java.util.List;
import javax.inject.Inject;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.joaogsma.citysuggestion.core.commands.SuggestCitiesCommand;
import org.joaogsma.citysuggestion.core.models.Suggestion;
import org.joaogsma.citysuggestion.entrypoint.models.ImmutableSuggestionsResponse;
import org.joaogsma.citysuggestion.entrypoint.models.SuggestionsResponse;

@Path(SuggestionsResource.PATH)
@Produces(MediaType.APPLICATION_JSON)
public class SuggestionsResource implements Resource {
  public static final String PATH = "/suggestions";
  public static final String SEARCH_TERM_QUERY_PARAM = "q";
  public static final String LAT_QUERY_PARAM = "latitude";
  public static final String LNG_QUERY_PARAM = "longitude";

  private static final int MAX_LAT = 90;
  private static final int MAX_LNG = 180;

  private final SuggestCitiesCommand command;

  @Inject
  public SuggestionsResource(SuggestCitiesCommand command) {
    this.command = command;
  }

  @GET
  public SuggestionsResponse get(
      @QueryParam(SEARCH_TERM_QUERY_PARAM) @NotEmpty String searchTerm,
      @QueryParam(LAT_QUERY_PARAM) @Min(-MAX_LAT) @Max(MAX_LAT) Double lat,
      @QueryParam(LNG_QUERY_PARAM) @Min(-MAX_LNG) @Max(MAX_LNG) Double lng) {
    System.out.println(
        String.format(
            "Received request with params %s=%s, %s=%s, %s=%s}",
            SEARCH_TERM_QUERY_PARAM, searchTerm, LAT_QUERY_PARAM, lat, LNG_QUERY_PARAM, lng));
    final List<Suggestion> suggestions = command.call(searchTerm, lat, lng);
    return ImmutableSuggestionsResponse.builder().suggestions(suggestions).build();
  }
}
