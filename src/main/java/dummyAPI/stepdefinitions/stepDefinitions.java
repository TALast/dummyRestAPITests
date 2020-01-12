package dummyAPI.stepdefinitions;

import dummyAPI.helper.EmployeeHelper;
import dummyAPI.model.DataColumn;
import dummyAPI.model.DataColumnDataTable;
import dummyAPI.model.Employee;
import io.cucumber.java8.En;

import java.util.Iterator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class stepDefinitions implements En{

    private EmployeeHelper employeeHelper = new EmployeeHelper();

    public stepDefinitions() {
        Given("^an employee with details$", (Employee employee) ->
            employeeHelper.addTestEmployee(employee)
        );

        When("^I request to create new entry$", () ->
            employeeHelper.createTestEmployee(employeeHelper.getTestEmployee())
        );

        Then("^response contains (\\w+) with( | not )value ([a-zA-Z !]+)$", (String element, String negative, String value) -> {
            assertThat(employeeHelper.getCreateCustomerResponseStatus()).as("Http Response is 200 OK").isEqualTo("success");
            if (negative.equals("not")) {
                assertThat(employeeHelper.getResponseElement(element)).as("Response status is " + value).isNotEqualTo(value);
            } else {
                assertThat(employeeHelper.getResponseElement(element)).as("Response status is " + value).isEqualTo(value);
            }
        });

        Then("^response data values match the employee$", () -> {
            System.out.println("RESPONSE: " + employeeHelper.getLastResponse());
            assertThat(employeeHelper.getTestEmployee().getName())
                .as("Employee's name is inserted correctly")
                .isEqualTo(employeeHelper.getResponseDataValue("name"));

            assertThat(employeeHelper.getTestEmployee().getSalary())
                .as("Employee's salary is inserted correctly")
                .isEqualTo(employeeHelper.getResponseDataValue("salary"));

            assertThat(employeeHelper.getTestEmployee().getAge())
                .as("Employee's age is inserted correctly")
                .isEqualTo(employeeHelper.getResponseDataValue("age"));
        });

        Then("^response contains (\\w+) with columns$", (String element, DataColumnDataTable columns) -> {
            // assert the data element is in the response
            assertThat(employeeHelper.validateObjectExists(element)).as("Response contains " + element + " element").isTrue();
            List<String> keys = employeeHelper.getElementKeys(element);

            Iterator<DataColumn> i = columns.getEntries().iterator();
            while (i.hasNext()) {
                assertThat(keys).as("Response contains " + element + " element").contains(i.next().getColumnName());
            }
        });

        Given("^an employee id ([0-9 -.]+)$", (String id) -> {
            employeeHelper.setTestEmployeeId(id);
        });

        When("^I request to delete the entry$", () -> {
           employeeHelper.deleteTestEmployee();
        });
        When("^I request the details of all employees$", () -> {
            employeeHelper.requestAllEmployees();
        });

        And("^response contains (\\w+) with array of entries$", (String element) -> {
            assertThat(employeeHelper.checkElementInResponseIsArray(element))
                .as("Validate element " + element + "is an array")
                .isTrue();
        });

        And("^each (\\w+) array entry contains the columns$", (String element, DataColumnDataTable columns) -> {
            for (int j = 0; j < employeeHelper.getResponseArrayLength(element); j++) {
                List<String> keys = employeeHelper.getArrayElementKeys(element, j);
                Iterator<DataColumn> i = columns.getEntries().iterator();
                while (i.hasNext()) {
                    assertThat(keys).as("Response contains " + element + " element").contains(i.next().getColumnName());
                }
            }

        });

        When("^I request the details of this employee$", () -> {
            employeeHelper.requestTestEmployee();
        });

        When("^I request to update his details to$", (Employee employee) -> {
            employeeHelper.updateTestEmployee(employee);
        });
    }
}
