package dummyAPI.model;

public class DataColumn {

    private String columnName;

    public DataColumn(String columnName) {
        this.columnName = columnName;
    }

    public static DataColumn createDataColumnDataTableEntry(String entry) {
        return new DataColumn(entry);
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }
}
