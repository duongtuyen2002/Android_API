package com.example.api;

import androidx.activity.result.contract.ActivityResultContract;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.example.api.Adapter.HttpAdapter;
import com.example.api.Adapter.MovieAdapter;
import com.example.api.Adapter.ShopAdapter;
import com.example.api.data.remote.APIService;
import com.example.api.Interface.IShopUpdate;
import com.example.api.data.remote.RetrofitClient;
import com.example.api.data.response.Movie;
import com.example.api.data.response.MovieResponse;
import com.example.api.model.ShopHTTP;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import static com.example.api.Untils.Config.*;

import java.lang.reflect.Type;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity implements IShopUpdate {
    Button button;
    RecyclerView recyclerView;
    List<Movie> movies;
    ShopAdapter shopAdapter;
    HttpAdapter httpAdapter;
    private MovieAdapter.RecycleViewListen listen;
    MovieAdapter movieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.rc_view);
        LinearLayoutManager layout = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layout);
        setOnClickListen();
        update();
    }

    private void setOnClickListen() {
        listen = new MovieAdapter.RecycleViewListen() {
            @Override
            public void onClick(View v, int position) {
                Intent intent = new Intent(getApplicationContext(), MovieDetail.class);
                startActivity(intent);
                Bundle bundle = new Bundle();
                bundle.putParcelable("movie", movies.get(position));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        };
    }

    @Override
    public void update() {
//            APIService apiService = RetrofitClient.getInstance().getAPIService();
//            Call<List<ShopHTTP>> call =apiService.LIST_CALL();
//            call.enqueue(new Callback<List<ShopHTTP>>() {
//                @Override
//                public void onResponse(Call<List<ShopHTTP>> call, retrofit2.Response<List<ShopHTTP>> response) {
//                    httpAdapter = new HttpAdapter(getApplicationContext(), response.body(),MainActivity.this);
//                    recyclerView.setAdapter(httpAdapter);
//                    httpAdapter.notifyDataSetChanged();
//                }
//
//                @Override
//                public void onFailure(Call<List<ShopHTTP>> call, Throwable t) {
//                    Log.e("App error", t.toString());
//                }
//            });
        (new OkHTTP()).execute();
    }

//    private class HTTPReqTask extends AsyncTask<Void, Void, String> {
//        @Override
//        protected String doInBackground(Void... params) {
//            HttpURLConnection urlConnection = null;
//            StringBuffer stringBuffer = new StringBuffer();
//            try {
//                URL url = new URL(BASE_URL_API);
//                urlConnection = (HttpURLConnection) url.openConnection();
//                int code = urlConnection.getResponseCode();
//                if (code != 200) {
//                    throw new IOException("Invalid response from server: " + code);
//                }
//                BufferedReader rd = new BufferedReader(new InputStreamReader(
//                        urlConnection.getInputStream()));
//                String line;
//                while ((line = rd.readLine()) != null) {
//                    stringBuffer.append(line);
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            } finally {
//                if (urlConnection != null) {
//                    urlConnection.disconnect();
//                }
//            }
//            return stringBuffer.toString();
//        }
//
//        @Override
//        protected void onPostExecute(String data) {
//            super.onPostExecute(data);
//            Gson gson = new Gson();
//            Type type = new TypeToken<List<Shop>>(){}.getType();
//            List<Shop> shops = gson.fromJson(data,type);
//            shopAdapter = new ShopAdapter(getApplicationContext(),shops);
//            recyclerView.setAdapter(shopAdapter);
//            shopAdapter.notifyDataSetChanged();
//        }
//    }

    private class OkHTTP extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... voids) {
            OkHttpClient client = new OkHttpClient();
            try {
                Request.Builder builder = new Request.Builder();
                builder.url(BASE_URL_API1);
                Request request = builder.build();
                Response response = client.newCall(request).execute();
                return response.body().string();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "[]";
        }

        @Override
        protected void onPostExecute(String data) {
            super.onPostExecute(data);
            Log.e("finish", "good");
            Gson gson = new Gson();
            Type type1 = new TypeToken<MovieResponse>() {
            }.getType();
            MovieResponse response = gson.fromJson(data, type1);
            movies = response.movies;
            movieAdapter = new MovieAdapter(getApplicationContext(), movies, MainActivity.this, listen);
            recyclerView.setAdapter(movieAdapter);
            movieAdapter.notifyDataSetChanged();
        }
    }

}