package doaaahmed.movie_app;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by ahmedalaa on 9/24/16.
 */
public class TJsonParser {

    String json;
    ArrayList<RT>  results;

    public TJsonParser(String s) {
        this.json = s;
        results = new ArrayList();
    }

    public void parse() throws JSONException {
        JSONObject obj = new JSONObject(json);
        JSONArray a = obj.getJSONArray("results");
        for (int i = 0; i < a.length(); i++) {
            results.add(new RT(true, a.getJSONObject(i).getString("name"), a.getJSONObject(i).getString("key")));
        }
    }

    public ArrayList<RT> getResults() throws JSONException {
        parse();
        return results;
    }
}
