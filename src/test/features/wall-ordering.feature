Feature: The wall should be ordered in the chronological order across all users'
  posts.

  Scenario: Wall ordered correctly for several people following
    Given "Alice" posts "Hi, I'm Alice"
    And   "Bob" posts "I'm Bob"
    And   "Alice" posts "Nice day today"
    And   "Charlie" posts "Hi, I'm Charlie"
    And   "Charlie" follows "Alice"
    When  "Charlie" views their wall
    Then  I see:
    """
    Charlie - Hi, I'm Charlie
    Alice - Nice day today
    Alice - Hi, I'm Alice
    """