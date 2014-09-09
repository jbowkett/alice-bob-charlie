package info.bowkett.abc;

import info.bowkett.abc.dal.*;
import info.bowkett.abc.shell.*;

/**
 * Created by jbowkett on 27/08/2014.
 */
public class Main {

  private final Shell shell;

  public Main(Shell shell) {
    this.shell = shell;
  }

  private void startShell() {
    shell.startShell();
  }

  /**
   * Main shell entry point
   * @param argsAreIgnored
   */
  public static void main(String [] argsAreIgnored){
    final UserRepository userRepo = new InMemoryUserRepository();
    final TimelineRepository timelineRepo = new InMemoryTimelineRepository();
    final FollowRepository followRepo = new InMemoryFollowRepository();
    final WallFactory wallFactory = new WallFactory(followRepo, timelineRepo);
    final CommandFactory commandFactory = new CommandFactory(userRepo, timelineRepo, followRepo, wallFactory);
    final Console console = new Console(new Timeformat());
    final Shell shell = new Shell(commandFactory, console);
    new Main(shell).startShell();
  }
}
