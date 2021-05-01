package com.thic.marvelmovies.Model.Adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.thic.marvelmovies.Model.Local.RoomModel;
import com.thic.marvelmovies.Model.models.Item;
import com.thic.marvelmovies.R;

import java.util.ArrayList;
import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.FavoritesHolder> {

    Context context;
    List<RoomModel> favoriteList;

    public FavoriteAdapter(Context context,List<RoomModel> favoriteList) {
        this.context = context;
        this.favoriteList = favoriteList;
    }

    @NonNull
    @Override
    public FavoritesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.priv_layout_favorites,parent,false);
        return new FavoritesHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoritesHolder holder, int position) {

        Glide.with(context)
                .load("https://image.tmdb.org/t/p/w500/"+favoriteList.get(position).getBackdropPath())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        holder.progressBar.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        holder.progressBar.setVisibility(View.GONE);
                        return false;
                    }
                })
                .centerCrop()
                .into(holder.movieImg);

        holder.movieText.setText(favoriteList.get(position).getMovieTitle());

        if (position >6) holder.layout.setPadding(0,0,0, 130);
        else holder.layout.setPadding(0,0,0, 0);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    @Override
    public int getItemCount() {
        return favoriteList.size();
    }

    public class FavoritesHolder extends RecyclerView.ViewHolder {

        ImageView movieImg;
        TextView movieText;
        ProgressBar progressBar;
        RelativeLayout layout;

        public FavoritesHolder(@NonNull View itemView) {
            super(itemView);
            movieImg = itemView.findViewById(R.id.favoriteImg);
            movieText = itemView.findViewById(R.id.favoriteTitle);
            progressBar = itemView.findViewById(R.id.favorite_progress);
            layout = itemView.findViewById(R.id.layoutFavorite);
        }
    }
}
