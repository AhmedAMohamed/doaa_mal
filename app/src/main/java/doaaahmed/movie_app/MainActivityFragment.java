package doaaahmed.movie_app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private final String popular_URL= "http://api.themoviedb.org/3/movie/popular?api_key=be0168c8674961cf754ebc2b5850f61c";
    private final String topRated_URL= "http://api.themoviedb.org/3/movie/top_rated?api_key=be0168c8674961cf754ebc2b5850f61c";

    public static ArrayList<Movie> movies = new ArrayList<>();

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    static Context context ;

    SharedPreferences prefs;
    SharedPreferences.Editor editor;

    public MainActivityFragment() {
    }

    public void options(boolean url){

        if(context == null){
            Log.d("null", "onCreateView: context null");
        }
        else{
            Log.d("not null", "onCreateView: context not null");
        }
        if (url == true) {
            movies.clear();
            //ionFunction(popular_URL, this.context);
        }
        else if (url!= true){
            movies.clear();
            //ionFunction(topRated_URL, this.context);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context = getContext();
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        //mRecyclerView.setHasFixedSize(true);
        // use grid layout
        mLayoutManager = new GridLayoutManager(getContext(),2);
        mRecyclerView.setLayoutManager(mLayoutManager);
        ((MainActivity)getActivity()).setFragmentRefreshListener(new MainActivity.FragmentRefreshListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(getContext(), "Ahmed loves Doaa'", Toast.LENGTH_LONG).show();
                movies.clear();
                prefs = getActivity().getSharedPreferences("settings",0);
                boolean pop = prefs.getBoolean("popularity", false);
                boolean top = prefs.getBoolean("top_rated", false);
                if (pop && ! top) {
                    MoviesViewerTask task_worker = new MoviesViewerTask(popular_URL);
                    task_worker.execute();
                }
                else if (!pop && top){
                    MoviesViewerTask task_worker = new MoviesViewerTask(topRated_URL);
                    task_worker.execute();
                }
                else {
                    MoviesViewerTask task_worker = new MoviesViewerTask(popular_URL);
                    task_worker.execute();
                }
            }
        });
        ((MainActivity)getActivity()).getFragmentRefreshListener().onRefresh();
        return view;
    }

    @Override
    public void onStart() {
        movies.clear();
        MoviesViewerTask task_worker = new MoviesViewerTask(popular_URL);
        task_worker.execute();


        super.onStart();
    }

    private class MoviesViewerTask extends AsyncTask<String, String, String> {
        String path;
        private StringBuilder stringBuilder;

        @Override
        protected String doInBackground(String... strings) {

            try {
                URL url = new URL(path);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                try {
                    urlConnection.setRequestMethod("GET");
                    urlConnection.setDoInput(true);
                    urlConnection.setDoOutput(true);
                    urlConnection.connect();

                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    stringBuilder = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line).append("\n");
                    }
                    bufferedReader.close();

                    MovieJsonParser parser = new MovieJsonParser(stringBuilder.toString());
                    parser.parse();
                    movies = parser.getData();

                }
                catch (Exception e) {
                    Log.e("error_in", e.toString());
                }
                finally{
                    urlConnection.disconnect();
                }
            }
            catch (Exception ex) {

            }

            return null;
        }

        public MoviesViewerTask(String url) {
            super();
            path = url;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            mAdapter = new MovieAdapter(getActivity(),getContext(),movies);
            mRecyclerView.setAdapter(mAdapter);
        }
    }



}
