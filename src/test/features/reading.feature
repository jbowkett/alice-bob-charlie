Feature:
  This feature ensures that an individual user's timeline is viewable at any time

  Reading: Bob can view Alice’s timeline

  > Alice
  I love the weather today (5 minutes ago)
  > Bob
  Good game though. (1 minute ago)
  Damn! We lost! (2 minutes ago)

  Background:
    Given "Alice" posts "I love the weather today"
    And   "Bob" posts "Damn! We lost!"
    And   "Bob" posts "Good game though."

  Scenario: Viewing all of the posts by one user
    When  reading the posts by "Alice"
    Then  I see
    """
    I love the weather today
    """

  Scenario: Viewing multiple posts by one user
    When  reading the posts by "Bob"
    Then  I see
    """
    Good game though.
    Damn! We lost!
    """
