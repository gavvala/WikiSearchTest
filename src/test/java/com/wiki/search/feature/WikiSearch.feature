@SmokeTest
Feature: Wiki Search feature
  This feature helps to search wiki with give search text

  Scenario: search wiki
    Given I navigate to wiki site
    When I enter "Selenium" search word in search text box
    And I select "English" as my search language.
    And I click search button
    Then I redirect to search results page successfully
    And validate the search results page title with "Selenium" search text
    And I can navigate to "German" language search page
    And I can see english search link



