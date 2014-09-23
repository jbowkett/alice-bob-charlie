package info.bowkett.abc.console;

import info.bowkett.abc.commands.Command;

import java.util.Scanner;

/**
 * Class representing console output.
 * Could also have handled console input, but did not want this class to
 * require to maintain any state
 * Created by jbowkett on 30/08/2014.
 */
public class Console {

  private final Timeformat time;
  private final CommandFactory commandFactory;

  public Console(Timeformat time, CommandFactory commandFactory) {
    this.time = time;
    this.commandFactory = commandFactory;
  }

  public Console print(String post) {
    System.out.print(post);
    return this;
  }

  /**
   * Outputs the difference between now and the given timestamp,
   * as formatted by the timeformat in the constructor
   * @param timestamp
   * @return
   */
  public Console timestamp(long timestamp) {
    return print('(' + time.forNow(timestamp) + ')');
  }

  public Console println() {
    return print("\n");
  }

  /**
   * Main interactive command shell
   */
  public void startShell() {
    final Scanner in = new Scanner(System.in);
    printPrompt();
    while (in.hasNext()) {
      final String currentLine = in.nextLine();
      submit(currentLine);
      printPrompt();
    }
  }

  private void printPrompt() {
    print("> ");
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
    final Command command = commandFactory.getCommand(shellCommand);
    command.execute(this);
  }
}
