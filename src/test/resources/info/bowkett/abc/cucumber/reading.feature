Feature:
  This feature ensures that an individual user's timeline is viewable at any time

  Reading: Bob can view Alice’s timeline

  > Alice
  I love the weather today (5 minutes ago)
  > Bob
  Good game though. (1 minute ago)
  Damn! We lost! (2 minutes ago)

  Background:
    Given a shell
    And  "Alice" posts "I love the weather today"
    And  "Bob" posts "Damn! We lost!"
    And  "Bob" posts "Good game though."

  @wip
  Scenario: Viewing all of the timestamped posts by one user
    When reading the posts by "Alice"
    Then I see "I love the weather today" with a timestamp