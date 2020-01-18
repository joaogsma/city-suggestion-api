package org.joaogsma.citysuggestion.core.dal;

import java.util.Set;
import java.util.function.Function;
import java.util.stream.Stream;

public interface InvertedIndex<K, V> {
  void add(V content, Function<V, Stream<K>> keysFunction);

  Set<String> get(Stream<String> keys);
}
