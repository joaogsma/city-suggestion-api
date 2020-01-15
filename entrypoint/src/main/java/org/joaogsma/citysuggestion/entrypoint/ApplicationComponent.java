package org.joaogsma.citysuggestion.entrypoint;

import dagger.Component;
import org.joaogsma.citysuggestion.entrypoint.modules.ConfigurationModule;
import org.joaogsma.citysuggestion.entrypoint.resources.HelloWorldResource;

@Component(modules = ConfigurationModule.class)
interface ApplicationComponent {
  HelloWorldResource helloWorldResource();
}
