package dummyAPI.utils;

import dummyAPI.model.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DummyAPIWorld {
    private List<Employee> systemEmployees;
    private List<Employee> testEmployees;

    public DummyAPIWorld() {
        systemEmployees = new ArrayList<Employee>();
        testEmployees = new ArrayList<>();
    }

    public void addSystemEmployee (Employee employee) {
        systemEmployees.add(employee);
    }

    public boolean employeeExists(String name, String salary, String age) {
        Optional<Employee> employee = systemEmployees.stream().filter(e -> (
            e.getName().equals(name) && e.getSalary().equals(salary) && e.getAge().equals(age)
        )).findFirst();

        return employee.isPresent();
    }

    public void addTestEmployee (Employee employee) {
        testEmployees.add(employee);
    }

    public Employee getTestEmployee() {
        // return only the first employee of the list
        return testEmployees.get(0);
    }

    public void setTestEmployeeId(String id) {
        testEmployees.add(new Employee(id));
    }
}
