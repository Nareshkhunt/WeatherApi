@getService
Feature: API get request Testing for OpenWeatherMap
  As a user
  I want to to be able get current weather data for one location
  So that I can validate that API functionality

  Scenario Outline: Validate weather data with valid data
    Given I have base uri
    When I execute get request with query param "<key>" and "<value>"
    Then I should see status code as "200"
    And I see response with values of "<name>","<id>","<longitude>","<latitude>"

    Examples:
      | key | value   | name          | id      | longitude | latitude |
      | q   | london  | London        | 2643743 | -0.1257   | 51.5085  |
      | id  | 2643743 | London        | 2643743 | -0.1257   | 51.5085  |
      | zip | 94040   | Mountain View | 0       | -122.088  | 37.3855  |

  Scenario: Validate weather data with valid data
    Given I have base uri
    When I execute get request by geographic coordinates with query params "51.51" "-0.13"
    Then I should see status code as "200"
    And I see response with values of "London","2643743","-0.13","51.51"


  Scenario Outline: Validate weather data without query parameters value
    Given I have base uri
    When I execute get request with query param "<key>" and "<value>"
    Then I should see status code as "400"
    And I should see the message body with "message" as "Nothing to geocode"

    Examples:
      | key | value |
      | q   |       |
      | id  |       |
      | zip |       |

  Scenario Outline: Validate weather data with wrong query parameters value
    Given I have base uri
    When I execute get request with query param "<key>" and "<value>"
    Then I should see status code as "404"
    And I should see the message body with "message" as "city not found"

    Examples:
      | key | value         |
      | q   | london12      |
      | id  | 264374398088  |
      | zip | 9404078998909 |

  Scenario Outline: Validate weather data with wrong or without query parameters
    Given I have base uri
    When I execute get request with query param "<key>" and "<value>"
    Then I should see status code as "400"
    And I should see the message body with "message" as "Nothing to geocode"

    Examples:
      | key  | value   |
      | q1   | london  |
      |      | 2643743 |
      | zip1 | 94040   |

  Scenario: Validate weather data with wrong Api key
    Given I have base uri
    When I execute get request with wrong api key and query param as "q" and "london"
    Then I should see status code as "401"
    And I should see the message body with "message" as "Invalid API key. Please see http://openweathermap.org/faq#error401 for more info."

  Scenario: Validate weather data without Api key
    Given I have base uri
    When I execute get request without api key and query param as "q" and "london"
    Then I should see status code as "401"
    And I should see the message body with "message" as "Invalid API key. Please see http://openweathermap.org/faq#error401 for more info."
