Feature: User is able to delete the entry of an employee

  Scenario: Delete an existing employee
    Given an employee id 1
    When I request to delete the entry
    Then response contains status with value success
    And response contains message with value successfully! deleted Records