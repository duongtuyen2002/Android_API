package com.example.api.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.api.MainActivity;
import com.example.api.R;
import com.example.api.model.Slide;

import java.util.List;

public class SlideAdapter extends PagerAdapter {

    Context context;
    private List<Slide> slides;

    public SlideAdapter(Context context, List<Slide> slides) {
        this.context = context;
        this.slides = slides;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {


        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View slideLayout = inflater.inflate(R.layout.dong_silde,null);

        ImageView slideImg = slideLayout.findViewById(R.id.img_slide);
        slideImg.setImageResource(slides.get(position).getImg());
        container.addView(slideLayout);
        return slideLayout;

    }

    @Override
    public int getCount() {
        return slides.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }

}
