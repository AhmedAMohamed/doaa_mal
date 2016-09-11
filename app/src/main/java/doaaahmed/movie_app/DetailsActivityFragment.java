package doaaahmed.movie_app;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailsActivityFragment extends Fragment {

    /*Intent i = getActivity().getIntent();
    Movie movie = (Movie) i.getSerializableExtra("movie");*/
    ImageView image;
    TextView plot;
    TextView title;
    TextView date;
    TextView rate;

    public DetailsActivityFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        Bundle m = getActivity().getIntent().getBundleExtra("movie");
        Movie movie = (Movie)m.getParcelable("movie");

        View v = inflater.inflate(R.layout.fragment_details, container, false);
        image = (ImageView) v.findViewById(R.id.posterthumb);
        plot = (TextView) v.findViewById(R.id.plot);
        title = (TextView) v.findViewById(R.id.title);
        date = (TextView) v.findViewById(R.id.date);
        rate = (TextView) v.findViewById(R.id.rate);

        Picasso.with(getContext())
                .load(movie.getPoster())
                .into(image);
        plot.setText(movie.getPlot());
        title.setText(movie.getTitle());
        date.setText(movie.getDate());
        rate.setText(movie.getRate());

        Toast.makeText(getContext(), "Ahmed Really loves Doaa", Toast.LENGTH_LONG).show();
        return v;
    }
}
