package doaaahmed.movie_app;

import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by ahmedalaa on 9/11/16.
 */
public class MovieJsonParser {
    String j_data;
    ArrayList<Movie> data;
    public MovieJsonParser(String s) {
        j_data = s;
        data = new ArrayList();
    }

    public void parse() throws JSONException {
        JSONObject json = new JSONObject(j_data);
        JSONArray results = json.getJSONArray("results");

        JSONObject jsonObject = null;
        for(int i = 0; i< results.length(); i++){
            jsonObject = results.getJSONObject(i);
            String title = jsonObject.get("original_title").toString();
            String poster = jsonObject.get("poster_path").toString();
            String plot = jsonObject.get("overview").toString();
            String rate = jsonObject.get("vote_average").toString();
            String date = jsonObject.get("release_date").toString();
            data.add(new Movie(rate, title, poster, plot, date));
        }
    }

    public ArrayList<Movie> getData() {
        return data;
    }
}
