package info.bowkett.abc.commands;

/**
 * Wall shell command
 * Created by jbowkett on 31/08/2014.
 */
public class WallCommand implements Command {
  private final String userName;

  public WallCommand(String userName) {
    this.userName = userName;
  }

  @Override
  public void execute() {

  }

  @Override
  public String getUserName() {
    return userName;
  }
}
