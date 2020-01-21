package org.joaogsma.citysuggestion.core.dal;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import java.util.AbstractMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Stream;
import javax.inject.Inject;

public class MemoryInvertedIndex<K, V> implements InvertedIndex<K, V> {
  private final Map<K, Set<V>> index;

  @Inject
  public MemoryInvertedIndex(Stream<V> values, Function<V, Stream<K>> keysFunction) {
    index =
        values
            .flatMap(
                value ->
                    keysFunction
                        .apply(value)
                        .map(
                            key ->
                                new AbstractMap.SimpleImmutableEntry<K, Set<V>>(
                                    key, ImmutableSet.of(value))))
            .collect(
                ImmutableMap.toImmutableMap(
                    AbstractMap.SimpleImmutableEntry::getKey,
                    AbstractMap.SimpleImmutableEntry::getValue,
                    Sets::union));
  }

  @Override
  public Set<V> get(Stream<K> keys) {
    return keys.filter(index::containsKey)
        .map(index::get)
        .reduce(Sets::union)
        .orElseGet(ImmutableSet::of);
  }
}
