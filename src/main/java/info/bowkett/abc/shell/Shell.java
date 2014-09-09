package info.bowkett.abc.shell;

import info.bowkett.abc.commands.*;
import info.bowkett.abc.dal.FollowRepository;
import info.bowkett.abc.dal.TimelineRepository;
import info.bowkett.abc.dal.UserRepository;
import info.bowkett.abc.domain.User;
import info.bowkett.abc.domain.Wall;

import java.util.Scanner;

/**
 * Main user interaction shell.
 * Reads user input, and delegates off to other components or prints the
 * results of operations to the console.
 * Created by jbowkett on 29/08/2014.
 */
public class Shell {
  private final CommandFactory parser;
  private final Console console;

  public Shell(CommandFactory parser, Console console) {
    this.parser = parser;
    this.console = console;
  }

  /**
   * Main interactive command shell, responds to "quit" in addition to the
   * requirements
   */
  public void startShell() {
    final Scanner in = new Scanner(System.in);
    boolean quit = false;
    console.print("> ");
    while (!quit && in.hasNext()) {
      final String currentLine = in.nextLine();
      quit = currentLine.equalsIgnoreCase("quit");
      if(!quit) {
        submit(currentLine);
        console.print("> ");
      }
    }
  }

  /**
   * Parses the given string into a command instance decorated with all the
   * details specified in shellCommand.
   * The permissible commands are:
   * <user> -> "A post"            = posting
   * <user>                        = viewing the user's posts
   * <user> wall                   = viewing the user's wall
   * <user> follows <another user> = following another user
   * @param shellCommand to parse
   */
  public void submit(String shellCommand) {
    final Command command = parser.getCommand(shellCommand);
    command.execute(console);
  }
}
