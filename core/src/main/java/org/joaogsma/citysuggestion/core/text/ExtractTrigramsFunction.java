package org.joaogsma.citysuggestion.core.text;

import com.google.common.base.Predicates;
import com.google.common.collect.ImmutableSet;
import java.util.Arrays;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.IntStream;
import javax.inject.Inject;

/**
 * Extracts a set of trigrams from a given string. The string is padded by two spaces before and one
 * space after every word; this adds extra trigrams enforcing the beginning and end of words. Only
 * word of whitespace characters are considered, the others are removed from the string.
 */
public class ExtractTrigramsFunction implements Function<String, Set<String>> {
  @Inject
  ExtractTrigramsFunction() {}

  @Override
  public Set<String> apply(String str) {
    final String[] words = str.toLowerCase().replaceAll("[^\\w\\s]", "").split("\\s");
    return Arrays.stream(words)
        .filter(Predicates.not(String::isEmpty))
        .flatMap(
            word ->
                IntStream.range(-2, word.length() - 1).mapToObj(index -> trigramAt(word, index)))
        .collect(ImmutableSet.toImmutableSet());
  }

  private String trigramAt(String str, int index) {
    return String.format(
        "%c%c%c",
        paddedCharAt(str, index), paddedCharAt(str, index + 1), paddedCharAt(str, index + 2));
  }

  private char paddedCharAt(String str, int index) {
    return index < 0 || index >= str.length() ? ' ' : str.charAt(index);
  }
}
