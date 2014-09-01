package info.bowkett.abc.commands;


/**
 * Command interface for commands issued in the shell
 * Created by jbowkett on 29/08/2014.
 */
public interface Command {
  /**
   * @return the userName of the user the command pertains to
   */
  public String getUserName();
}
