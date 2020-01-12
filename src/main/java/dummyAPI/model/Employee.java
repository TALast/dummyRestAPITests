package dummyAPI.model;

import java.util.Map;

public class Employee {

    private String name;
    private String salary;
    private String age;
    private String id;

    public Employee(String name, String salary, String age) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public Employee(String id) {
        this.id = id;
    }

    public static Employee createEmployeeDataTableEntry(Map<String, String> entry) {
        return new Employee(
                entry.get("name"),
                entry.get("salary"),
                entry.get("age"));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String toString() {
        return new String("Name: " + name + ", Salary: " + salary + ", Age: " + age);
    }
}
