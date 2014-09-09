package info.bowkett.abc.commands;

/**
 * Read user's posts shell command
 * Created by jbowkett on 30/08/2014.
 */
public class ReadCommand implements Command {
  private final String userName;

  public ReadCommand(String userName) {
    this.userName = userName;
  }

  @Override
  public String getUserName() {
    return userName;
  }
}
