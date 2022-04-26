package com.example.api.data.response;

import com.google.gson.annotations.SerializedName;

public class Movie {
    @SerializedName("id")
    public int id;

    @SerializedName("title")
    public String title;

    @SerializedName("backdrop_path")
    public String backdropPath;
}
