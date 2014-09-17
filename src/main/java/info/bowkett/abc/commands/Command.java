package info.bowkett.abc.commands;


import info.bowkett.abc.console.Console;

/**
 * Command interface for commands issued in the shell
 * Created by jbowkett on 29/08/2014.
 */
public interface Command {

  void execute(Console console);

}
