package info.bowkett.abc.commands;

/**
 * Created by jbowkett on 30/08/2014.
 */
public class ViewCommand implements Command {
  private final String userName;

  public ViewCommand(String userName) {
    this.userName = userName;
  }

  @Override
  public String getUserName() {
    return userName;
  }
}
