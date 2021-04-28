package com.thic.marvelmovies.Model.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.thic.marvelmovies.Model.models.Item;
import com.thic.marvelmovies.R;

import java.util.List;

public class SliderAdapter extends PagerAdapter {
    private Context context;
    private List<Item> urlList;

    public SliderAdapter(Context context, List<Item> urlList) {
        this.context = context;
        this.urlList = urlList;
    }

    @Override
    public int getCount() {
        return urlList.size() ;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        Item current = urlList.get(position);

        View root = LayoutInflater.from(context).inflate(R.layout.private_banner_layout,container,false);

        ImageView bannerMovie = root.findViewById(R.id.banner_movie);
        TextView bannerMovie_Title = root.findViewById(R.id.bannerTitle);
        TextView bannerMovie_imdb = root.findViewById(R.id.banner_imdb);

        Glide.with(context)
                .load("https://image.tmdb.org/t/p/w500/"+urlList.get(position).getBackdropPath())
                .centerCrop()
                .into(bannerMovie);


        bannerMovie_Title.setText(current.getTitle());
        bannerMovie_imdb.setText(current.getVoteAverage().toString());

        container.addView(root);
        return root;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        container.removeView((View)object);
    }
}
