package com.thic.marvelmovies.UI.fragments;

import android.os.Bundle;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.like.LikeButton;
import com.like.OnLikeListener;
import com.thic.marvelmovies.Model.Local.RoomModel;
import com.thic.marvelmovies.R;
import com.thic.marvelmovies.Viewmodel.ViewmodelData;

import java.util.ArrayList;
import java.util.List;


public class BottomSheet extends BottomSheetDialogFragment {


    TextView movieTitleT,movieDateT,movieLang,movieImdb,movieOverView;
    ImageView movieImg;
    LikeButton favorite;
    ViewmodelData viewmodel;
    List<RoomModel> localData = new ArrayList<>();

    //  Data
    private int movieID;
    private String movieTitle;
    private String movieDate;
    private String movieLanguage;
    private String movieIMDb;
    private String overview;
    private String BackdropPath;

    private RoomModel newModel;


    public BottomSheet() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewmodel = new ViewModelProvider(this).get(ViewmodelData.class);
        localData = viewmodel.getFavoriteList().getValue();
        getValue();
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_bottom_sheet, container, false);


        initializeVariable(root);
        setValue();
        newModel = new RoomModel(movieID,movieTitle,movieDate,movieLanguage,movieIMDb,overview,BackdropPath);

        viewmodel.getFavoriteList().observe(getViewLifecycleOwner(), new Observer<List<RoomModel>>() {
            @Override
            public void onChanged(List<RoomModel> roomModels) {
                for (RoomModel r : roomModels){
                    if (r.getMovieID()==movieID){
                        favorite.setLiked(true);
                    }
                }
            }
        });

        Glide.with(getActivity().getApplicationContext())
                .load("https://image.tmdb.org/t/p/w500/"+BackdropPath)
                .centerCrop()
                .into(movieImg);

        favorite.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {
                viewmodel.insert(newModel);
            }

            @Override
            public void unLiked(LikeButton likeButton) {
                viewmodel.del(newModel);
            }
        });

        return root;
    }

    private void initializeVariable(View root) {
        movieImg = root.findViewById(R.id.movieFocusImg);
        movieTitleT = root.findViewById(R.id.FocusTitle);
        movieDateT = root.findViewById(R.id.FocusDate);
        movieLang = root.findViewById(R.id.FocusLanguage);
        movieImdb = root.findViewById(R.id.FocusImdb);
        movieOverView = root.findViewById(R.id.OverView);
        favorite = root.findViewById(R.id.star_button);
    }

    private void setValue() {
        movieTitleT.setText(movieTitle);
        movieDateT.setText(movieDate);
        movieLang.setText(movieLanguage);
        movieImdb.setText(movieIMDb);
        movieOverView.setText(overview);
    }
    private void getValue() {
        movieID = getArguments().getInt("movieID");
        movieTitle = getArguments().getString("movieTitle");
        movieDate =getArguments().getString("movieDate");
        movieLanguage = getArguments().getString("movieLanguage").toUpperCase();
        movieIMDb = getArguments().getString("movieIMDB");
        overview = getArguments().getString("movieOverview");
        BackdropPath = getArguments().getString("movieImg");
    }
}