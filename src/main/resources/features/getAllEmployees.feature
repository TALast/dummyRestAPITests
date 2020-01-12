Feature: User is able to retrieve the details of all employees

  Scenario: Retrieve the details of all existing employees
    When I request the details of all employees
    Then response contains status with value success
    And response contains data with array of entries
    And each data array entry contains the columns
      | id              |
      | employee_name   |
      | employee_salary |
      | employee_age    |
      | profile_image   |