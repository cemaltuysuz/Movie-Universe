package com.thic.marvelmovies.UI.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.like.LikeButton;
import com.like.OnLikeListener;
import com.thic.marvelmovies.Model.Local.RoomModel;
import com.thic.marvelmovies.Model.models.CategoryModel;
import com.thic.marvelmovies.Model.models.Movie;
import com.thic.marvelmovies.R;
import com.thic.marvelmovies.Viewmodel.ViewmodelData;

import java.util.ArrayList;
import java.util.List;


public class BottomSheet extends BottomSheetDialogFragment {


    TextView movieTitle,movieDate,movieLang,movieImdb,movieOverView;
    ImageView movieImg;
    LikeButton favorite;
    ViewmodelData viewmodel;
    List<RoomModel> localData = new ArrayList<>();
    private boolean check = false;
    private int movieID,favoriteID;


    public BottomSheet() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewmodel = new ViewModelProvider(this).get(ViewmodelData.class);
        localData = viewmodel.getFavoriteList().getValue();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_bottom_sheet, container, false);

        movieID = getArguments().getInt("movieID");

        movieImg = root.findViewById(R.id.movieFocusImg);
        movieTitle = root.findViewById(R.id.FocusTitle);
        movieDate = root.findViewById(R.id.FocusDate);
        movieLang = root.findViewById(R.id.FocusLanguage);
        movieImdb = root.findViewById(R.id.FocusImdb);
        movieOverView = root.findViewById(R.id.OverView);
        favorite = root.findViewById(R.id.star_button);
        setValue();

        viewmodel.getFavoriteList().observe(getViewLifecycleOwner(), new Observer<List<RoomModel>>() {
            @Override
            public void onChanged(List<RoomModel> roomModels) {
                for (RoomModel r : roomModels){
                    if (r.getMovieID()==movieID){
                        favorite.setLiked(true);
                        favoriteID = r.getFavoriteID();
                    }
                }
            }
        });
        Glide.with(getActivity().getApplicationContext())
                .load("https://image.tmdb.org/t/p/w500/"+getArguments().getString("movieImg"))
                .centerCrop()
                .into(movieImg);

        favorite.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {
                viewmodel.insert(new RoomModel(movieID));
            }

            @Override
            public void unLiked(LikeButton likeButton) {
                viewmodel.del(new RoomModel(movieID));
            }
        });

        return root;
    }

    private void setValue() {
        movieTitle.setText(getArguments().getString("movieTitle"));
        movieDate.setText(getArguments().getString("movieDate"));
        movieLang.setText(getArguments().getString("movieLanguage").toUpperCase());
        movieImdb.setText(getArguments().getString("movieIMDB"));
        movieOverView.setText(getArguments().getString("movieOverview"));
    }
}