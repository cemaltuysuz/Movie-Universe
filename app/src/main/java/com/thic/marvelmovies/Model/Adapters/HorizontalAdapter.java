package com.thic.marvelmovies.Model.Adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.ImageViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.thic.marvelmovies.Model.models.Item;
import com.thic.marvelmovies.R;
import com.thic.marvelmovies.Viewmodel.ViewmodelData;

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

        holder.movieTitle.setText(movieList.get(position).getTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewmodelData.clickListener.setValue(movieList.get(position));
            }
        });
    }


    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class HorizontalHolder extends RecyclerView.ViewHolder {

        ImageView movieImg;
        TextView movieTitle;
        ProgressBar progressBar;

        public HorizontalHolder(@NonNull View itemView) {
            super(itemView);

            movieImg = itemView.findViewById(R.id.movieImg);
            movieTitle = itemView.findViewById(R.id.movieTitle);
            progressBar = itemView.findViewById(R.id.single_movie_progress);
        }
    }
}
