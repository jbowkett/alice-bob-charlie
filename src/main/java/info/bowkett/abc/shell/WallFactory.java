package info.bowkett.abc.shell;

import info.bowkett.abc.dal.FollowRepository;
import info.bowkett.abc.dal.TimelineRepository;
import info.bowkett.abc.domain.Subscriptions;
import info.bowkett.abc.domain.Timeline;
import info.bowkett.abc.domain.User;
import info.bowkett.abc.domain.Wall;

import java.util.stream.Stream;

/**
 * Assembles a user's wall by interleaving the posts by the people the user
 * is following with the user's own posts
 * Created by jbowkett on 01/09/2014.
 */
public class WallFactory {

  private final FollowRepository followRepo;
  private final TimelineRepository timelineRepo;

  public WallFactory(FollowRepository followRepo, TimelineRepository timelineRepo) {
    this.followRepo = followRepo;
    this.timelineRepo = timelineRepo;
  }

  public Wall getWall(User user) {
    final Subscriptions subscriptions = followRepo.getSubscriptionsFor(user);
    final Stream<Timeline> timelinesForOthers = subscriptions.stream().map(timelineRepo::get);
    final Timeline userTimeline = timelineRepo.get(user);
    final Wall wall = new Wall();
    timelinesForOthers.forEach(timeline -> timeline.forEachRecentFirst(wall::add));
    userTimeline.forEachRecentFirst(wall::add);
    return wall;
  }
}
