package com.thic.marvelmovies.UI.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.thic.marvelmovies.Model.Adapters.FavoriteAdapter;
import com.thic.marvelmovies.Model.Local.RoomModel;
import com.thic.marvelmovies.Model.models.Item;
import com.thic.marvelmovies.R;
import com.thic.marvelmovies.Viewmodel.ViewmodelData;

import java.util.ArrayList;
import java.util.List;


public class favorites extends Fragment {

    RecyclerView recyclerView;
    FavoriteAdapter favoriteAdapter;
    ViewmodelData viewmodel;

    public favorites() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewmodel = new ViewModelProvider(this).get(ViewmodelData.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_favorites, container, false);

        recyclerView = root.findViewById(R.id.favoriteRecyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity().getApplicationContext(),2));
        recyclerView.setHasFixedSize(true);

        viewmodel.getFavoriteList().observe(getViewLifecycleOwner(), new Observer<List<RoomModel>>() {
            @Override
            public void onChanged(List<RoomModel> roomModels) {
                if (roomModels.size()>0){
                    favoriteAdapter = new FavoriteAdapter(getActivity().getApplicationContext(),roomModels);
                    recyclerView.setAdapter(favoriteAdapter);

                }
            }
        });


        return root;
    }
}