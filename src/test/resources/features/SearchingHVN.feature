@Smoke
  @Search
Feature: Going to https://www.hitachivantara.com/ through Google search

  # An example for PASSED cased
  Scenario: Verify that the user can open hitachivantara.com through Google search
    Given I'm on the Google search
    When I enter "Hitachi Vantara com" into search text box
    And I click Search button
    Then I verify that "Hitachi Vantara" site is at the first in search result

#  # An example for FAILED cased
#  Scenario: Verify that the user can open hitachivantara.com through Google search
#    Given I'm on the Google search
#    When I enter "Global Cybersoft" into search text box
#    And I click Search button
#    Then I verify that "Hitachi Vantara" site is at the first in search result