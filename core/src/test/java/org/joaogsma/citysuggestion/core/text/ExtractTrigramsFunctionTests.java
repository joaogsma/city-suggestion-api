package org.joaogsma.citysuggestion.core.text;

import static org.assertj.core.api.Assertions.assertThat;

import com.google.common.collect.ImmutableSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

public class ExtractTrigramsFunctionTests {
  private final String TEXT = "Lead city";
  private final Set<String> TRIGRAMS =
      ImmutableSet.of("  c", "  l", " ci", " le", "ad ", "cit", "ead", "ity", "lea", "ty ");

  private final ExtractTrigramsFunction function = new ExtractTrigramsFunction();

  @Test
  void shouldExtractTrigramsSuccessfully() {
    assertThat(function.apply(TEXT)).isEqualTo(TRIGRAMS);
  }

  @Test
  void shouldIgnoreLeadingAndTrailingWhitespace() {
    final String text = "   " + TEXT + "  ";
    assertThat(function.apply(text)).isEqualTo(TRIGRAMS);
  }

  @Test
  void whenThereAreNonWordNonWhitespaceCharacters_shouldIgnoreThem() {
    final String text = "   &" + TEXT.replace("a", "@a") + ".  ";
    assertThat(function.apply(text)).isEqualTo(TRIGRAMS);
  }
}
