package org.joaogsma.citysuggestion.core.text;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.google.common.collect.ImmutableSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

public class TrigramSimilarityFunctionTests {
  private final Set<String> TRIGRAMS1 =
      ImmutableSet.of(" ri", " su", "  s", "sin", "ris", "ng ", "  r", "su ", "ing", "isi");
  private final Set<String> TRIGRAMS2 =
      ImmutableSet.of(
          "  r", " ri", "ban", "sun", "leb", "-le", " su", "  s", "n-l", "eba", "ris", "ng ", "ing",
          "isi", "un-", "sin", "ano", "on ", "non");
  private final double SIMILARITY = 0.45;

  private final TrigramSimilarityFunction function = new TrigramSimilarityFunction();

  @Test
  void shouldComputeTheSimilarityCorrectly() {
    assertThat(function.apply(TRIGRAMS1, TRIGRAMS2)).isEqualTo(SIMILARITY);
  }

  @Test
  void whenBothTrigramSetsAreEmpty_shouldThrowAnException() {
    assertThatThrownBy(() -> function.apply(ImmutableSet.of(), ImmutableSet.of()))
        .isInstanceOf(IllegalArgumentException.class);
  }
}
