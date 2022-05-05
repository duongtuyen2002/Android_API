package com.example.api.data.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Movie implements Parcelable{
    @SerializedName("id")
    public int id;

    @SerializedName("title")
    public String title;

    @SerializedName("release_date")
    public String release_date;

    @SerializedName("overview")
    public String overview;

    @SerializedName("backdrop_path")
    public String backdropPath;

    protected Movie(Parcel in) {
        id = in.readInt();
        title = in.readString();
        backdropPath = in.readString();
        release_date = in.readString();
        overview = in.readString();
    }

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(title);
        parcel.writeString(backdropPath);
        parcel.writeString(release_date);
        parcel.writeString(overview);
    }
}
