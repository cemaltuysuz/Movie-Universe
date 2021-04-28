package com.thic.marvelmovies.Model.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.widget.ImageViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.thic.marvelmovies.Model.models.Item;
import com.thic.marvelmovies.R;

import org.w3c.dom.Text;

import java.util.List;

public class HorizontalAdapter extends RecyclerView.Adapter<HorizontalAdapter.HorizontalHolder> {

    Context context;
    List<Item> movieList;

    public HorizontalAdapter(Context context, List<Item> movieList) {
        this.context = context;
        this.movieList = movieList;
    }

    @NonNull
    @Override
    public HorizontalHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.priv_layout_horizontal,parent,false);
        return new HorizontalHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HorizontalHolder holder, int position) {

        Glide.with(context)
                .load("https://image.tmdb.org/t/p/w500/"+movieList.get(position).getPosterPath())
                .centerCrop()
                .into(holder.movieImg);

        holder.movieTitle.setText(movieList.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class HorizontalHolder extends RecyclerView.ViewHolder {

        ImageView movieImg;
        TextView movieTitle;

        public HorizontalHolder(@NonNull View itemView) {
            super(itemView);

            movieImg = itemView.findViewById(R.id.movieImg);
            movieTitle = itemView.findViewById(R.id.movieTitle);
        }
    }
}
