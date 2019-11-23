package dropwizard.scaffolding.entrypoint;

import dagger.Component;
import dropwizard.scaffolding.entrypoint.modules.ConfigurationModule;

@Component(modules = ConfigurationModule.class)
interface ApplicationComponent {}
