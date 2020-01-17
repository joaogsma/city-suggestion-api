package org.joaogsma.citysuggestion.core.models;

import org.immutables.value.Value;

@Value.Immutable
public interface City {
  String name();

  double lat();

  double lng();
}
