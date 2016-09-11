package doaaahmed.movie_app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import java.util.ArrayList;

/**
 * Created by hp on 8/13/2016.
 */
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieHolder>{

    private ArrayList<Movie> movies;
    Context context;
    Activity activity;

    public MovieAdapter(Activity activity, Context context, ArrayList<Movie> data){
        this.movies = data;
        this.context = context;
        this.activity = activity;
    }
    public void add(int position, Movie item) {
        movies.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(Movie item) {
        int position = movies.indexOf(item);
        movies.remove(position);
        notifyItemRemoved(position);
    }
    @Override
    public MovieHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_layout, parent, false);
        MovieHolder mh = new MovieHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(MovieHolder holder, final int position) {
       // Picasso.with(context).load(movies.get(position).getPoster()).into(holder.poster);

        Picasso.with(context)
                .load(movies.get(position).getPoster())
                .into(holder.poster);

        Log.d("poster",  movies.get(position).getPoster());
        Log.d("plot",  movies.get(position).getPlot());

        holder.poster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Bundle data = new Bundle();
                data.putParcelable("movie", movies.get(position));

                Intent intent = new Intent(activity.getApplicationContext(), DetailsActivity.class);
                intent.putExtra("movie", data);
                activity.startActivity(intent);

                //movies.get(position)
            }
        });

    }

    @Override
    public int getItemCount() {

        return movies.size();
    }

    public class MovieHolder extends RecyclerView.ViewHolder{

        public ImageView poster;
       /* public TextView plot;
        public TextView title;
        public TextView date;
        public TextView rate;*/

        public MovieHolder(View itemView) {
            super(itemView);
            poster = (ImageView) itemView.findViewById(R.id.imageView);
           /* plot = (TextView) itemView.findViewById(R.id.plot);
            title = (TextView) itemView.findViewById(R.id.title);
            date = (TextView) itemView.findViewById(R.id.date);
            rate = (TextView) itemView.findViewById(R.id.rate);
*/
        }
    }
}
