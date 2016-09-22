package doaaahmed.movie_app;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ahmedalaa on 9/20/16.
 */
public class RT implements Parcelable{

    boolean trailer;
    String title;
    String content;

    public RT() {

    }

    public RT(boolean trailer, String title, String content) {
        this.trailer = trailer;
        this.title = title;
        this.content = content;
    }

    protected RT(Parcel in) {
        trailer = in.readByte() != 0;
        title = in.readString();
        content = in.readString();
    }

    public static final Creator<RT> CREATOR = new Creator<RT>() {
        @Override
        public RT createFromParcel(Parcel in) {
            return new RT(in);
        }

        @Override
        public RT[] newArray(int size) {
            return new RT[size];
        }
    };

    public boolean isTrailer() {
        return trailer;
    }

    public void setTrailer(boolean trailer) {
        this.trailer = trailer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "RT{" +
                "trailer=" + trailer +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte((byte) (trailer ? 1 : 0));
        parcel.writeString(title);
        parcel.writeString(content);
    }
}
