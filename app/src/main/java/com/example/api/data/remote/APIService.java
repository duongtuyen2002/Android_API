package com.example.api.data.remote;

import com.example.api.model.ShopHTTP;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface APIService {
    @GET("products")
    Call<List<ShopHTTP>> LIST_CALL();

    @DELETE("products/{id}")
    Call<Void> deleteProducts(@Path("id") int id);
}
