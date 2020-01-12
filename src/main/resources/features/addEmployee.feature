Feature: User is able to add create employee

  Scenario: Create employee a new employee
    Given an employee with details
      | name | salary | age |
      | test | 123    | 23  |
    When I request to create new entry
    Then response contains status with value success
    And response contains data with columns
      | name   |
      | salary |
      | age    |
      | id     |
    And response data values match the employee

    Scenario Outline: Create employee with empty fields than required
      Given an employee with details
        | name   | salary   | age   |
        | <name> | <salary> | <age> |
      When I request to create new entry
      Then response contains status with value success
      And response contains data with columns
        | name   |
        | salary |
        | age    |
        | id     |
      And response data values match the employee

      Examples:
        | name | salary | age |
        |      | 143    | 18  |
        | test |        | 19  |
        | test | 132    |     |