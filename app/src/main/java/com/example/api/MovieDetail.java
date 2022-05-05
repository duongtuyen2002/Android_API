package com.example.api;

import static com.example.api.Untils.Config.BASE_BACKDROP_PATH;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import  com.example.api.Untils.Config;
import com.example.api.data.response.Movie;
import com.squareup.picasso.Picasso;

public class MovieDetail extends AppCompatActivity {

    ImageView img_anh;
    TextView tvid,tvname,tvdate,tvover;
    ImageButton img_bt_back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.moviedetail);

        tvid = findViewById(R.id.textViewidd);
        tvname = findViewById(R.id.textViewtitlee);
        tvdate = findViewById(R.id.textViewdatee);
        tvover = findViewById(R.id.textViewoverview);
        img_anh = findViewById(R.id.imageViewHinhAnhh);
        img_bt_back = findViewById(R.id.img_bt_back);

        img_bt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MovieDetail.this,MainActivity.class);
                startActivity(intent);
            }
        });

        Bundle bundle = getIntent().getExtras();
        if (bundle == null){
            return;
        }
        Movie mMovie = (Movie) bundle.get("movie");
        tvid.setText("Id : "+ mMovie.id + "");
        tvname.setText("Title : "+mMovie.title);
        tvdate.setText("Date : "+mMovie.release_date);
        tvover.setText("OverView : "+mMovie.overview);

//        holder.tvOverView.setText(mMovie.overview);
//        holder.tvCreate.setText(mMovie.createdAt);
        Picasso.with(getApplicationContext()).load(BASE_BACKDROP_PATH + mMovie.backdropPath).into(img_anh);
    }
}