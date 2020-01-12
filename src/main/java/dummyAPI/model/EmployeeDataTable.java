package dummyAPI.model;

import java.util.List;

public class EmployeeDataTable {
    private List<Employee> entries;

    public EmployeeDataTable() {
    }

    public EmployeeDataTable (List<Employee> entries) {
        this.entries = entries;
    }

    public List<Employee> getEntries(){
        return this.entries;
    }
}