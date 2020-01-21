package org.joaogsma.citysuggestion.core.dal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Set;
import java.util.stream.Stream;
import org.joaogsma.citysuggestion.core.models.City;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class InvertedIndexFacadeTests {
  @Mock private Stream<String> KEY_STREAM;
  @Mock private Set<City> CITY_SET;

  @Mock private InvertedIndex<String, City> index;
  @InjectMocks private InvertedIndexFacade facade;

  @Test
  void shouldCallIndex() {
    when(index.get(KEY_STREAM)).thenReturn(CITY_SET);
    assertThat(facade.get(KEY_STREAM)).isEqualTo(CITY_SET);
    verify(index).get(KEY_STREAM);
  }
}
