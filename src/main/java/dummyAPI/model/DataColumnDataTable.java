package dummyAPI.model;

import java.util.List;

public class DataColumnDataTable {
    private List<DataColumn> columns;

    public DataColumnDataTable() {}

    public DataColumnDataTable(List<DataColumn> columns) {
        this.columns = columns;
    }

    public List<DataColumn> getEntries(){
        return this.columns;
    }
}
