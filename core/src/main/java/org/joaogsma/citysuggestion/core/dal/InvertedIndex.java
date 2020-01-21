package org.joaogsma.citysuggestion.core.dal;

import java.util.Set;
import java.util.stream.Stream;

public interface InvertedIndex<K, V> {
  Set<V> get(Stream<K> keys);
}
