package org.joaogsma.citysuggestion.entrypoint.modules;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoSet;
import org.joaogsma.citysuggestion.entrypoint.resources.Resource;
import org.joaogsma.citysuggestion.entrypoint.resources.SuggestionsResource;

@Module
public abstract class ResourceModule {
  @Binds
  @IntoSet
  public abstract Resource bindSuggestionsResource(SuggestionsResource resource);
}
