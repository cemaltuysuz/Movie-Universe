package com.thic.marvelmovies.Viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.thic.marvelmovies.Model.models.Item;
import com.thic.marvelmovies.Model.Local.RoomModel;
import com.thic.marvelmovies.Model.models.Movie;
import com.thic.marvelmovies.Model.Network.API;
import com.thic.marvelmovies.Model.Network.ApiUtils;
import com.thic.marvelmovies.Model.Repository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyViewmodel extends AndroidViewModel {

    private MutableLiveData<List<Item>> movieList = new MutableLiveData<>();
    private LiveData<List<RoomModel>> favoriteList;

    Repository repository;
    private API dataApi = ApiUtils.api();

    public MyViewmodel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
        favoriteList = repository.getAllData();
    }

    //   ----------- Action methods -----------    //

    //   Local Methods
    public void insert(RoomModel model){
        repository.insert(model);
    }
    public void del(RoomModel model){
        repository.delete(model);
    }

    //   Network Methods
    public MutableLiveData<List<Item>> getMovieData(){
        movieList.postValue(repository.getAllMovies());
        return movieList;
    }


    //Get Lists
    public LiveData<List<RoomModel>> getFavoriteList(){ return favoriteList;
    }

}
