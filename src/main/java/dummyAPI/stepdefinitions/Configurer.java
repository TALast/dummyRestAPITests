package dummyAPI.stepdefinitions;

import dummyAPI.model.DataColumn;
import dummyAPI.model.DataColumnDataTable;
import dummyAPI.model.Employee;
import dummyAPI.model.EmployeeDataTable;
import io.cucumber.core.api.TypeRegistry;
import io.cucumber.core.api.TypeRegistryConfigurer;
import io.cucumber.datatable.DataTable;
import io.cucumber.datatable.DataTableType;
import io.cucumber.datatable.TableTransformer;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

public class Configurer implements TypeRegistryConfigurer {
    @Override
    public Locale locale() {
        return Locale.ENGLISH;
    }

    @Override
    public void configureTypeRegistry(TypeRegistry typeRegistry) {

        typeRegistry.defineDataTableType(new DataTableType(Employee.class,
                (Map<String, String> row) -> {
                    return new Employee(row.get("name"), row.get("salary"), row.get("age"));
                }));

        typeRegistry.defineDataTableType(new DataTableType(EmployeeDataTable.class, new TableTransformer<EmployeeDataTable>() {
            @Override
            public EmployeeDataTable transform(DataTable table) throws Throwable {
                List<Employee> entries = table.asMaps().stream().map(Employee::createEmployeeDataTableEntry).collect(Collectors.toList());
                return new EmployeeDataTable(entries);
            }
        }));

        typeRegistry.defineDataTableType(new DataTableType(DataColumnDataTable.class, new TableTransformer<DataColumnDataTable>() {
            @Override
            public DataColumnDataTable transform(DataTable table) throws Throwable {
                List<DataColumn> entries = table.asList().stream().map(DataColumn::createDataColumnDataTableEntry).collect(Collectors.toList());
                return new DataColumnDataTable(entries);
            }
        }));
    }
}
