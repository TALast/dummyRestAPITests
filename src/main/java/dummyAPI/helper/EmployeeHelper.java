package dummyAPI.helper;

import dummyAPI.lib.DummyAPIJSONParser;
import dummyAPI.lib.IteratorTransformer;
import dummyAPI.lib.RequestSender;
import dummyAPI.model.Employee;
import dummyAPI.utils.DummyAPIWorld;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

public class EmployeeHelper {
    private DummyAPIWorld dummyAPIWorld;
    private RequestSender request;
    private String lastResponse;

    public EmployeeHelper() {
        dummyAPIWorld = new DummyAPIWorld();
    }

    public void addTestEmployee(Employee employee) {
        dummyAPIWorld.addTestEmployee(employee);
    }

    public void setTestEmployeeId(String id) {
        dummyAPIWorld.setTestEmployeeId(id);
    }

    public void createTestEmployee(Employee employee) {
        request = new RequestSender("http://dummy.restapiexample.com/api/v1/create");

        request.setBody(buildEmployeeBody(employee));

        request.makeHttpPostRequest();
        if (request.getStatus() == 200) {
            try {
                lastResponse = request.getResponseBody();
            } catch (IOException e) {
                e.printStackTrace();
            }
            dummyAPIWorld.addSystemEmployee(employee);
        }
    }

    public void deleteTestEmployee() {
        request = new RequestSender("http://dummy.restapiexample.com/api/v1/employee/" + dummyAPIWorld.getTestEmployee().getId());
        try {
            request.makeHttpGetRequest();
            if (request.getStatus() == 200) {
                lastResponse = request.getResponseBody();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void requestAllEmployees() {
        request = new RequestSender("http://dummy.restapiexample.com/api/v1/employees");
        try {
            request.makeHttpGetRequest();
            if (request.getStatus() == 200) {
                lastResponse = request.getResponseBody();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void requestTestEmployee() {
        request = new RequestSender("http://dummy.restapiexample.com/api/v1/employee/" + dummyAPIWorld.getTestEmployee().getId());

        try {
            request.makeHttpGetRequest();
            if (request.getStatus() == 200) {
                lastResponse = request.getResponseBody();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateTestEmployee(Employee employee) {

        request = new RequestSender("http://dummy.restapiexample.com/api/v1/update/" + dummyAPIWorld.getTestEmployee().getId());
        request.setBody(buildEmployeeBody(employee));

        try {
            request.makeHttpPutRequest();
            if (request.getStatus() == 200) {
                lastResponse = request.getResponseBody();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        dummyAPIWorld.getTestEmployee().setName(employee.getName());
        dummyAPIWorld.getTestEmployee().setSalary(employee.getSalary());
        dummyAPIWorld.getTestEmployee().setAge(employee.getAge());
    }

    public String getCreateCustomerResponseStatus() {
        JSONObject jsonResponse = DummyAPIJSONParser.getJSONObjectFromString(lastResponse);
        return jsonResponse.getString("status");
    }

    public boolean validateObjectExists(String object) {
        JSONObject jsonResponse = DummyAPIJSONParser.getJSONObjectFromString(lastResponse);
        return jsonResponse.getJSONObject(object) != null;
    }

    public String getResponseElement(String element) {
        JSONObject jsonResponse = DummyAPIJSONParser.getJSONObjectFromString(lastResponse);
        return jsonResponse.getString(element);
    }

    public List<String> getElementKeys(String object) {
        JSONObject jsonResponse = DummyAPIJSONParser.getJSONObjectFromString(lastResponse);
        return IteratorTransformer.getListFromIterator((jsonResponse.getJSONObject(object).keys()));
    }

    public List<String> getArrayElementKeys(String element, int index) {
        JSONObject jsonResponse = DummyAPIJSONParser.getJSONObjectFromString(lastResponse);
        return IteratorTransformer.getListFromIterator((jsonResponse.getJSONArray(element).getJSONObject(index).keys()));
    }

    public Employee getTestEmployee () {
        return dummyAPIWorld.getTestEmployee();
    }

    public String getResponseDataValue(String element) {
        JSONObject jsonResponse = DummyAPIJSONParser.getJSONObjectFromString(lastResponse);

        if (jsonResponse.getJSONObject("data").isNull(element)) {
            return new String("");
        }
        return jsonResponse.getJSONObject("data").getString(element);
    }

    public boolean checkElementInResponseIsArray(String element) {
        JSONObject jsonResponse = DummyAPIJSONParser.getJSONObjectFromString(lastResponse);

        Object item = jsonResponse.get(element);
        if (item instanceof JSONArray) {
            return true;
        } else {
            return false;
        }
    }

    public int getResponseArrayLength(String element) {
        JSONObject jsonResponse = DummyAPIJSONParser.getJSONObjectFromString(lastResponse);
        return jsonResponse.getJSONArray(element).length();
    }

    public void loadExistingEmployees(String element) {
    }

    public String buildEmployeeBody(Employee employee) {
        String reqBody = new String("{");
        boolean comma = false;
        if (!employee.getName().isEmpty()) {
            reqBody = reqBody.concat("\"name\":\"" + employee.getName() + "\"");
            comma = true;
        }
        if (!employee.getSalary().isEmpty()) {
            if (comma) {
                reqBody = reqBody.concat(",");
            }
            reqBody = reqBody.concat("\"salary\":\"" + employee.getSalary() + "\"");
            comma = true;
        }
        if (!employee.getAge().isEmpty()) {
            if (comma) {
                reqBody = reqBody.concat(",");
            }
            reqBody = reqBody.concat("\"age\":\"" + employee.getAge() + "\"");
        }
        reqBody = reqBody.concat("}");

        return reqBody;
    }

    public String getLastResponse() {
        return lastResponse;
    }
}
