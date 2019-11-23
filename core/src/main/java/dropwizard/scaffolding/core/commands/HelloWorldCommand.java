package dropwizard.scaffolding.core.commands;

import javax.inject.Inject;

public class HelloWorldCommand {
  @Inject
  HelloWorldCommand() {}

  public String call() {
    return "Hello, world!";
  }
}
