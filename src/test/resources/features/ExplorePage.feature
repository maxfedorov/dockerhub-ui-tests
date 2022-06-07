Feature: Explore page
  Verify how explore page works

  Scenario Outline: Search image
    Given the user opens explore page
    And the user searches image '<image>'
    When the user clicks image '<image>'
    Then the user verifies that image page is open
    And the user verifies that image name is '<image>'
    And the user verifies that description contains text '<desc>'
    Examples:
      | image  | desc                  |
      | alpine | What is Alpine Linux? |
      | ubuntu | What is Ubuntu?       |