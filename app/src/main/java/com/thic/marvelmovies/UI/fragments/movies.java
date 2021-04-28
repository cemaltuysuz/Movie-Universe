package com.thic.marvelmovies.UI.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.transition.Slide;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.thic.marvelmovies.MainActivity;
import com.thic.marvelmovies.Model.Adapters.SliderAdapter;
import com.thic.marvelmovies.Model.Adapters.VerticalAdapter;
import com.thic.marvelmovies.Model.models.CategoryModel;
import com.thic.marvelmovies.Model.models.Item;
import com.thic.marvelmovies.R;
import com.thic.marvelmovies.Viewmodel.ViewmodelData;

import java.util.ArrayList;
import java.util.List;

public class movies extends Fragment {

    private ViewPager viewPager;
    private List<Item> movies = new ArrayList<>();
    private SliderAdapter adapter;
    private ViewmodelData viewmodel;
    private RecyclerView recyclerView;
    private VerticalAdapter verticalAdapter;

    public movies() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewmodel = new ViewModelProvider(requireActivity()).get(ViewmodelData.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_movies, container, false);

        viewPager = root.findViewById(R.id.MainViewPager);
        recyclerView = root.findViewById(R.id.verticalRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext(),
                LinearLayoutManager.VERTICAL,false));

        viewmodel.getMovies().observe(getViewLifecycleOwner(), new Observer<List<CategoryModel>>() {
            @Override
            public void onChanged(List<CategoryModel> categoryModels) {
                if (categoryModels.size()>0){
                    adapter = new SliderAdapter(getActivity().getApplicationContext(),
                            categoryModels.get(categoryModels.size()-1).getMovieList());
                    viewPager.setAdapter(adapter);

                    verticalAdapter = new VerticalAdapter(getActivity().getApplicationContext(),categoryModels);
                    recyclerView.setAdapter(verticalAdapter);
                }
            }
        });
        return root;
    }
}