@Test
Feature: Test Price filtering

  Background:
    Given user is on the Clippings Products search page
    And  filter menu is opened

  Scenario: Filter by min and max price
    And user selects Lighting from the hierarchical menu
    And user selects Light Bulbs from the hierarchical menu
    And user selects the following price filters
      | Min | Max |
      | 10  | 50  |
    Then verify product list contains only items priced between 10 and 50
    And verify items are listed in GBP