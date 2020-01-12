Feature: User is able to retrieve the details of a specific employee

  Scenario: Retrieve the details of existing employee
    Given an employee id 1
    When I request the details of this employee
    And response contains status with value success
    And response contains data with columns
      | id              |
      | employee_name   |
      | employee_salary |
      | employee_age    |
      | profile_image   |

    Scenario Outline: Retrieve the details using the id of a non existing employee
      Given an employee id <non_existing_id>
      When I request the details of this employee
      Then response contains status with not value success

    Examples:
        | non_existing_id |
        | 1000000         |
        | -10             |
        | 0.1             |