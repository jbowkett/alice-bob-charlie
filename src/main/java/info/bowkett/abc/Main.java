package info.bowkett.abc;

import info.bowkett.abc.dal.*;
import info.bowkett.abc.shell.*;

/**
 * Created by jbowkett on 27/08/2014.
 */
public class Main {

  private final Console console;

  public Main(Console console) {
    this.console = console;
  }

  private void startShell() {
    console.startShell();
  }

  /**
   * Main console app entry point
   * @param argsAreIgnored
   */
  public static void main(String [] argsAreIgnored){
    final UserRepository userRepo = new InMemoryUserRepository();
    final TimelineRepository timelineRepo = new InMemoryTimelineRepository();
    final FollowRepository followRepo = new InMemoryFollowRepository();
    final DataRepository dataRepo = new DataRepositoryImpl(userRepo, timelineRepo, followRepo);
    final CommandFactory commandFactory = new CommandFactory(dataRepo);
    final Console console = new Console(new Timeformat(), commandFactory);
    new Main(console).startShell();
  }
}
