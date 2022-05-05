package com.example.api.Adapter;

import static com.example.api.Untils.Config.BASE_URL_API;

import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.api.Interface.IShopUpdate;
import com.example.api.R;
import com.example.api.data.remote.APIService;
import com.example.api.data.remote.RetrofitClient;
import com.example.api.model.ShopHTTP;
import com.squareup.picasso.Picasso;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class HttpAdapter extends RecyclerView.Adapter<HttpAdapter.ViewHolder> {
    Context context;
    List<ShopHTTP> shops;
    IShopUpdate iShopUpdate;
    public HttpAdapter(Context context, List<ShopHTTP> shops,IShopUpdate iShopUpdate) {
        this.context = context;
        this.shops = shops;
        this.iShopUpdate = iShopUpdate;
    }

    @NonNull
    @Override
    public HttpAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_http,parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HttpAdapter.ViewHolder holder, int position) {
        ShopHTTP https = shops.get(position);
        holder.Name.setText(https.name);
        holder.price.setText(https.price);
        Picasso.with(context).load(https.url).into(holder.img);
        holder.Del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                APIService apiService = RetrofitClient.getInstance().getAPIService();
                apiService.deleteProducts(https.id).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        iShopUpdate.update();
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {

                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return shops.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView Name, price;
        ImageView img;
        Button Del;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Name = itemView.findViewById(R.id.tv_name_http);
            price = itemView.findViewById(R.id.tv_price_http);
            img = itemView.findViewById(R.id.img_avatar_http);
        }
    }
}
