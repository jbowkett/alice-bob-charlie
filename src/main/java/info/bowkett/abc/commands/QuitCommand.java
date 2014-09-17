package info.bowkett.abc.commands;

import info.bowkett.abc.shell.Console;

/**
 * Created by jbowkett on 17/09/2014.
 */
public class QuitCommand implements Command {
  @Override
  public void execute(Console console) {
    console.print("Quitting...").println();
    System.exit(0);
  }
}
