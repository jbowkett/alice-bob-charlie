package info.bowkett.abc.commands;

import info.bowkett.abc.dal.DataRepository;
import info.bowkett.abc.domain.Post;
import info.bowkett.abc.domain.User;
import info.bowkett.abc.console.Console;

/**
 * Post message shell command
 * Created by jbowkett on 29/08/2014.
 */
public class PostCommand implements Command {
  private final String userName;
  private final String postText;
  private final DataRepository dataRepository;

  public PostCommand(String user, String postText, DataRepository dataRepository) {
    this.userName = user;
    this.postText = postText;
    this.dataRepository = dataRepository;
  }

  @Override
  public void execute(Console console){
    final User user = dataRepository.getUser(userName);
    dataRepository.getTimeline(user).add(new Post(user, postText));
  }

  public String getText() {
    return postText;
  }

  public String getUserName() {
    return userName;
  }
}
