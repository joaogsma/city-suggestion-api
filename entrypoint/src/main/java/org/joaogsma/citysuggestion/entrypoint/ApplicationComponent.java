package org.joaogsma.citysuggestion.entrypoint;

import dagger.Component;
import java.util.Set;
import org.joaogsma.citysuggestion.entrypoint.modules.ConfigurationModule;
import org.joaogsma.citysuggestion.entrypoint.modules.ResourceModule;
import org.joaogsma.citysuggestion.entrypoint.resources.Resource;

@Component(modules = {ConfigurationModule.class, ResourceModule.class})
interface ApplicationComponent {
  Set<Resource> resources();
}
