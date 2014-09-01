Feature:
  This feature ensures That a user can subscribe to another user's updates and
  view it in their wall

  Following: Charlie can subscribe to Alice’s and Bob’s timelines, and view an aggregated list of all subscriptions

  > Charlie -> I'm in New York today! Anyone want to have a coffee?
  > Charlie follows Alice
  > Charlie wall
  Charlie - I'm in New York today! Anyone want to have a coffee? (2 seconds ago)
  Alice - I love the weather today (5 minutes ago)

  > Charlie follows Bob
  > Charlie wall
  Charlie - I'm in New York today! Anyone want to have a coffee? (15 seconds ago)
  Bob - Good game though. (1 minute ago)
  Bob - Damn! We lost! (2 minutes ago)
  Alice - I love the weather today (5 minutes ago)

  Background:
    Given "Alice" posts "I love the weather today"
    And   "Bob" posts "Damn! We lost!"
    And   "Bob" posts "Good game though."
    And   "Charlie" posts "I'm in New York today! Anyone want to have a coffee?"

Scenario: A user can post and subscribe to another user's posts and view them
  integrated in order on their wall
    When  "Charlie" follows "Alice"
    And   "Charlie" views their wall
    Then  I see the wall contains:
    """
    Charlie - I'm in New York today! Anyone want to have a coffee?
    Alice - I love the weather today
    """


  @wip
  Scenario: A user can post and subscribe to many users' posts and view them all
  integrated in order on their wall
    Given "Charlie" follows "Alice"
    When  "Charlie" follows "Bob"
    Then  "Charlie's" wall contains 4 posts
