package com.example.api.Adapter;

import static com.example.api.Untils.Config.BASE_BACKDROP_PATH;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.api.Interface.IShopUpdate;
import com.example.api.MainActivity;
import com.example.api.R;
import com.example.api.data.response.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    RecycleViewListen listen;
    Context context;
    List<Movie> movies;
    IShopUpdate iShopUpdate;

    public MovieAdapter(Context context, List<Movie> movies, IShopUpdate iShopUpdate, RecycleViewListen listen) {
        this.context = context;
        this.movies = movies;
        this.iShopUpdate = iShopUpdate;
        this.listen = listen;
    }

//    public MovieAdapter(Context context, List<Movie> movies, MainActivity iShopUpdate) {
//        this.context = context;
//        this.movies = movies;
//        this.iShopUpdate = iShopUpdate;
//    }

    @NonNull
    @Override
    public MovieAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.dong_http,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.ViewHolder holder, int position) {
        Movie movie = movies.get(position);
        holder.date.setText(movie.release_date);
        holder.title.setText(movie.title);
        Picasso.with(context).load(BASE_BACKDROP_PATH  + movie.backdropPath).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public interface RecycleViewListen{
        void onClick(View v, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView date, title;
        ImageView img;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.tv_name_http);
            title = itemView.findViewById(R.id.tv_price_http);
            img = itemView.findViewById(R.id.img_avatar_http);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listen.onClick(view, getAdapterPosition());
        }
    }
}
