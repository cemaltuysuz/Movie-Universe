package com.thic.marvelmovies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;
import android.util.Log;
import com.thic.marvelmovies.Model.models.Item;
import com.thic.marvelmovies.Model.models.Movie;
import com.thic.marvelmovies.Model.Network.API;
import com.thic.marvelmovies.Model.Network.ApiUtils;
import com.thic.marvelmovies.Viewmodel.MyViewmodel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    MyViewmodel viewmodel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewmodel = new ViewModelProvider(this,new
                ViewModelProvider.AndroidViewModelFactory(getApplication())).get(MyViewmodel.class);

    }
}