Feature:
  This Feature ensures that users can publish to their own individual timeline.
  Original requirement:
  "
  Posting: Alice can publish messages to a personal timeline
  > Alice -> I love the weather today
  > Bob -> Damn! We lost!
  > Bob -> Good game though.
  "

  Scenario: A user can post to their timeline
    When "Alice" posts "I love the weather today"
    Then "Alice's" timeline contains the post "I love the weather today"

  Scenario: A user can post multiple times to their timeline
    When "Bob" posts "Damn! We lost!"
    And  "Bob" posts "Good game though."
    Then "Bob's" timeline contains 2 posts

  Scenario: Ensure several users posting only do so to their own timeline
    When "Alice" posts "I love the weather today"
    And  "Bob" posts "Damn! We lost!"
    And  "Bob" posts "Good game though."
    Then "Bob's" timeline contains 2 posts
    And  "Alice's" timeline contains 1 posts

