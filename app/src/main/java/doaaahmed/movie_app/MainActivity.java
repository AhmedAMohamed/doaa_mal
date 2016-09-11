package doaaahmed.movie_app;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

   /* public final String popular_URL= "http://api.themoviedb.org/3/movie/popular?api_key=be0168c8674961cf754ebc2b5850f61c";
    public final String topRated_URL= "http://api.themoviedb.org/3/movie/top_rated?api_key=be0168c8674961cf754ebc2b5850f61c";
   */

    SharedPreferences prefs;
    SharedPreferences.Editor editor;



    public FragmentRefreshListener getFragmentRefreshListener() {
        return fragmentRefreshListener;
    }

    public void setFragmentRefreshListener(FragmentRefreshListener fragmentRefreshListener) {
        this.fragmentRefreshListener = fragmentRefreshListener;
    }

    private FragmentRefreshListener fragmentRefreshListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        prefs = getSharedPreferences("settings", 0);
        editor = prefs.edit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
         //Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id){
            case R.id.popularity:{
                editor.putBoolean("popularity", true);
                editor.putBoolean("top_rated", false);
                editor.apply();
                if(getFragmentRefreshListener()!=null){
                    getFragmentRefreshListener().onRefresh();
                }
                break;
            }
            case R.id.top_rated:{
                editor.putBoolean("popularity", false);
                editor.putBoolean("top_rated", true);
                editor.apply();
                if(getFragmentRefreshListener()!=null){
                    getFragmentRefreshListener().onRefresh();
                }
                break;
            }
        }
        return true;
    }

    public interface FragmentRefreshListener{
        void onRefresh();
    }
}
