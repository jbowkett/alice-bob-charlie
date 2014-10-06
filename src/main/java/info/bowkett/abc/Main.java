package info.bowkett.abc;

import info.bowkett.abc.datastore.*;
import info.bowkett.abc.console.*;

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
    final UserDAO userRepo = new UserDAOInMemory();
    final TimelineDAO timelineRepo = new TimelineDAOInMemory();
    final FollowDAO followRepo = new FollowDAOInMemory();
    final DataRepository dataRepo = new DataRepositoryImpl(userRepo, timelineRepo, followRepo);
    final CommandFactory commandFactory = new CommandFactory(dataRepo);
    final Console console = new Console(new Timeformat(), commandFactory);
    new Main(console).startShell();
  }
}
