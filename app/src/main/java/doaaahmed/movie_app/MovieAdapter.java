package doaaahmed.movie_app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by ahmedalaa on 9/15/16.
 */


class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieHolder>{

    private ArrayList<Movie> movies;
    Context context;
    Activity activity;

    public MovieAdapter(Activity activity, Context context, ArrayList<Movie> data){
        this.movies = data;
        this.context = context;
        this.activity = activity;
    }

    public ArrayList<Movie> getMovies() {
        return movies;
    }

    public void setMovies(ArrayList<Movie> movies) {
        this.movies = movies;
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

                //FragmentTransaction t = getActivity().getSupportFragmentManager()
                //        .beginTransaction();
                //Fragment mFrag = new DetailsActivityFragment();
                //mFrag.setArguments(data);

                //t.replace(R.id.details_frame,mFrag);
                //t.commit();
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

        public MovieHolder(View itemView) {
            super(itemView);
            poster = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }
}