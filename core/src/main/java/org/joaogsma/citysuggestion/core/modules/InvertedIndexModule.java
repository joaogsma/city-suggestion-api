package org.joaogsma.citysuggestion.core.modules;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import dagger.Module;
import dagger.Provides;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Stream;
import org.joaogsma.citysuggestion.core.dal.InvertedIndex;
import org.joaogsma.citysuggestion.core.dal.MemoryInvertedIndex;
import org.joaogsma.citysuggestion.core.models.City;
import org.joaogsma.citysuggestion.core.text.ExtractTrigramsFunction;

@Module
public class InvertedIndexModule {
  private final List<City> cities;

  public InvertedIndexModule(String filename) {
    cities = readFromFile(filename);
  }

  @Provides
  Stream<City> provideCityStream() {
    return cities.stream();
  }

  @Provides
  Function<City, Stream<String>> provideKeysFunction(ExtractTrigramsFunction fn) {
    return fn.compose(City::name).andThen(Set::stream);
  }

  @Provides
  InvertedIndex<String, City> provideInvertedIndex(MemoryInvertedIndex<String, City> index) {
    return index;
  }

  private List<City> readFromFile(String filename) {
    final ObjectMapper mapper = new ObjectMapper();
    final JavaType type = mapper.getTypeFactory().constructParametricType(List.class, City.class);

    try {
      final BufferedReader reader = new BufferedReader(new FileReader(filename));
      return mapper.readValue(reader, type);
    } catch (JsonProcessingException e) {
      throw new IllegalArgumentException(String.format("File %s is not a valid JSON", filename), e);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
