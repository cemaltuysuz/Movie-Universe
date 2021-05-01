package com.thic.marvelmovies;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.thic.marvelmovies.Model.models.Item;
import com.thic.marvelmovies.UI.fragments.favorites;
import com.thic.marvelmovies.UI.fragments.movies;
import com.thic.marvelmovies.Viewmodel.ViewmodelData;

import eightbitlab.com.blurview.BlurView;
import eightbitlab.com.blurview.RenderScriptBlur;

public class moviesContainer extends Fragment {

    BlurView blurView;
    BottomNavigationView bottomNavigationView;
    Bundle data;

    public moviesContainer() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        data = new Bundle();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movies_container, container, false);

        changeFragment(ViewmodelData.fragmentMutableLiveData.getValue());

        bottomNavigationView = view.findViewById(R.id.bottomBar);
        blurView = view.findViewById(R.id.blurView);
        setSettings();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int currentItem = bottomNavigationView.getSelectedItemId();
                int clickItem = item.getItemId();

                switch (clickItem){
                    case R.id.ic_home:
                        if (currentItem == R.id.ic_favorite){
                            changeFragment(new movies());
                        }
                    break;

                    case R.id.ic_favorite:
                        if (currentItem == R.id.ic_home){
                            changeFragment(new favorites());
                        }
                        break;
                }
                return true;
            }
        });

        ViewmodelData.clickListener.observe(getViewLifecycleOwner(), new Observer<Item>() {
            @Override
            public void onChanged(Item item) {
                data.putInt("movieID",item.getId());
                data.putString("movieTitle",item.getTitle());
                data.putString("movieDate",item.getReleaseDate());
                data.putString("movieLanguage",item.getOriginalLanguage());
                data.putString("movieIMDB",item.getVoteAverage().toString());
                data.putString("movieImg",item.getBackdropPath());
                data.putString("movieOverview",item.getOverview());
                Navigation.findNavController(view).navigate(R.id.action_moviesContainer_to_bottomSheet,data);
            }
        });
        return view;
    }

    private void setSettings() {
        float radius = 20f;

        View decorView = getActivity().getWindow().getDecorView();
        ViewGroup rootView = (ViewGroup) decorView.findViewById(android.R.id.content);
        Drawable windowBackground = decorView.getBackground();

        blurView.setupWith(rootView)
                .setFrameClearDrawable(windowBackground)
                .setBlurAlgorithm(new RenderScriptBlur(getActivity().getApplicationContext()))
                .setBlurRadius(radius)
                .setBlurAutoUpdate(true)
                .setHasFixedTransformationMatrix(true);

    }

    public void changeFragment (Fragment fragment){
        getFragmentManager().beginTransaction().replace(R.id.framelayout,fragment).commit();
    }
}