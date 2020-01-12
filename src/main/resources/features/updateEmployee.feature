Feature: User is able to update the details of an employee

  Scenario Outline: Update the details of an existing employee
    Given an employee id 1
    When I request to update his details to
      | name   | salary   | age   |
      | <name> | <salary> | <age> |
    Then response contains data with columns
      | name   |
      | salary |
      | age    |
      | id     |
    And response data values match the employee

    Examples:
      | name | salary | age |
      | Nick | 1234   | 40  |