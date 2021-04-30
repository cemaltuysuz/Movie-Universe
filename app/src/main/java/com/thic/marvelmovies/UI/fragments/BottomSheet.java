package com.thic.marvelmovies.UI.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.thic.marvelmovies.Model.models.CategoryModel;
import com.thic.marvelmovies.Model.models.Movie;
import com.thic.marvelmovies.R;


public class BottomSheet extends BottomSheetDialogFragment {


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
        return root;
    }
}