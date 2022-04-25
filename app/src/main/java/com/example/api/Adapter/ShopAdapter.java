package com.example.api.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.api.Interface.IShopUpdate;
import com.example.api.R;
import com.example.api.model.Shop;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ViewHolder> {

    Context context;
    List<Shop> shops;
    IShopUpdate iShopUpdate;

    public ShopAdapter(Context context, List<Shop> shops, IShopUpdate iShopUpdate) {
        this.context = context;
        this.shops = shops;
        this.iShopUpdate = iShopUpdate;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.dong_shop,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Shop shop = shops.get(position);
        holder.Name.setText(shop.name);
        holder.price.setText(shop.price);
        Picasso.with(context).load(shop.url).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return shops.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView Name, price;
        ImageView img;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Name = itemView.findViewById(R.id.tv_name);
            price = itemView.findViewById(R.id.tv_price);
            img = itemView.findViewById(R.id.img_avatar);
        }
    }
}
