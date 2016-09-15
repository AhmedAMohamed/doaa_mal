package doaaahmed.movie_app;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hp on 9/3/2016.
 */
public class Movie implements Serializable, Parcelable{
    /*
    * original title
movie poster image thumbnail
A plot synopsis (called overview in the api)
user rating (called vote_average in the api)
release date*/
    private String title;
    private String poster;
    private String plot;
    private String rate;
    private String date;
    private String id;
    private ArrayList<Review> reviews;
    private ArrayList<Video> trailers;

    public Movie(String id, String rate, String title, String poster, String plot, String date) {
        this.rate = rate.substring(0, rate.length());
        this.title = title.substring(0, title.length());
        this.poster = "http://image.tmdb.org/t/p/w185/" + poster.substring(1,poster.length());
        this.plot = plot.substring(0, plot.length());
        this.date = date.substring(0, date.length());
        this.id = id;
    }


    protected Movie(Parcel in) {
        title = in.readString();
        poster = in.readString();
        plot = in.readString();
        rate = in.readString();
        date = in.readString();
        id = in.readString();
        reviews = in.readArrayList(null);
        trailers = in.readArrayList(null);

    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public String getPoster() { return poster; }

    public String getRate() {
        return rate;
    }

    public String getPlot() {
        return plot;
    }

    public String getDate() {
        return date;
    }

    public ArrayList<Review> getReviews() {
        return reviews;
    }

    public void setReviews(ArrayList<Review> reviews) {
        this.reviews = reviews;
    }

    public ArrayList<Video> getTrailers() {
        return trailers;
    }

    public void setTrailers(ArrayList<Video> trailers) {
        this.trailers = trailers;
    }

    public String getId() {
        return id;
    }

    public static Creator<Movie> getCREATOR() {
        return CREATOR;
    }

    public void setTitle(String title) {

        this.title = title;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void addTrailer(Video v) {
        trailers.add(v);
    }

    public void addReview(Review r) {
        reviews.add(r);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(poster);
        parcel.writeString(plot);
        parcel.writeString(rate);
        parcel.writeString(date);
        parcel.writeString(id);
        parcel.writeList(reviews);
        parcel.writeList(trailers);
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", poster='" + poster + '\'' +
                ", plot='" + plot + '\'' +
                ", rate='" + rate + '\'' +
                ", date='" + date + '\'' +
                ", id='" + id + '\'' +
                ", reviews=" + reviews +
                ", trailers=" + trailers +
                '}';
    }

    public Map toJson() {
        Map<String, String> map = new HashMap();
        map.put("title", title);
        map.put("poster", poster);
        map.put("plot", plot);
        map.put("rate", rate);
        map.put("date", date);
        map.put("id", id);

        return null;
    }
}
