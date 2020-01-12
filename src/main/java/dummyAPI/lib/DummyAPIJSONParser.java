package dummyAPI.lib;

import org.json.JSONArray;
import org.json.JSONObject;

public class DummyAPIJSONParser {

    public static String getStringFromJSON(JSONObject json) {
        return json.toString();
    }

    public static JSONObject getJSONObjectFromString(String jsonString) {
        return new JSONObject(jsonString);
    }

    public boolean validateArray (JSONObject jsonObject) {
        JSONArray arr = jsonObject.getJSONArray("data");
        return arr.length() > 0;
    }

    public String getValue(String element, JSONObject jsonObject) {
        return jsonObject.getString(element);
    }
}
