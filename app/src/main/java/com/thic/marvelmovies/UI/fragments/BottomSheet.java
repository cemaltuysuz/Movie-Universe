package com.thic.marvelmovies.UI.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.thic.marvelmovies.Model.models.CategoryModel;
import com.thic.marvelmovies.Model.models.Movie;
import com.thic.marvelmovies.R;


public class BottomSheet extends BottomSheetDialogFragment {


    TextView movieTitle,movieDate,movieLang,movieImdb,movieOverView;
    ImageView movieImg;

    public BottomSheet() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_bottom_sheet, container, false);

        movieImg = root.findViewById(R.id.movieFocusImg);
        movieTitle = root.findViewById(R.id.FocusTitle);
        movieDate = root.findViewById(R.id.FocusDate);
        movieLang = root.findViewById(R.id.FocusLanguage);
        movieImdb = root.findViewById(R.id.FocusImdb);
        movieOverView = root.findViewById(R.id.OverView);

        movieTitle.setText(getArguments().getString("movieTitle"));
        movieDate.setText(getArguments().getString("movieDate"));
        movieLang.setText(getArguments().getString("movieLanguage").toUpperCase());
        movieImdb.setText(getArguments().getString("movieIMDB"));
        movieOverView.setText(getArguments().getString("movieOverview"));

        Glide.with(getActivity().getApplicationContext())
                .load("https://image.tmdb.org/t/p/w500/"+getArguments().getString("movieImg"))
                .centerCrop()
                .into(movieImg);

        return root;
    }
}