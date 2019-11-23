package dropwizard.scaffolding.entrypoint;

import dagger.Component;
import dropwizard.scaffolding.entrypoint.modules.ConfigurationModule;
import dropwizard.scaffolding.entrypoint.resources.HelloWorldResource;

@Component(modules = ConfigurationModule.class)
interface ApplicationComponent {
  HelloWorldResource helloWorldResource();
}
