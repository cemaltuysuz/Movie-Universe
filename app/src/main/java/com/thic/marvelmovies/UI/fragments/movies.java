package com.thic.marvelmovies.UI.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
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
import java.util.Collections;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;

public class movies extends Fragment {

    private ViewPager viewPager;
    private SliderAdapter adapter;
    private ViewmodelData viewmodel;
    private RecyclerView recyclerView;
    private VerticalAdapter verticalAdapter;
    private CircleIndicator indicator;
    private boolean isReverse = false;
    List<CategoryModel> categoryModels = new ArrayList<>();

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
        indicator = root.findViewById(R.id.circle_indicator);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext(),
                LinearLayoutManager.VERTICAL,false));

        ViewmodelData.networkResult.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {

                    if (aBoolean){
                        categoryModels = viewmodel.getMovies().getValue();
                        adapter = new SliderAdapter(getActivity().getApplicationContext(),
                                viewmodel.getSliderList().get(0));
                        viewPager.setAdapter(adapter);

                        verticalAdapter = new VerticalAdapter(getActivity().getApplicationContext(),categoryModels);
                        recyclerView.setAdapter(verticalAdapter);
                        indicator.setViewPager(viewPager);
                    }else {
                        Toast.makeText(getActivity().getApplicationContext(),"Network eror.",Toast.LENGTH_SHORT).show();
                    }
                }
        });

        return root;
    }
}