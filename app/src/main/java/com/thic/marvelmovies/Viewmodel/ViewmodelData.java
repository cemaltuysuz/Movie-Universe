package com.thic.marvelmovies.Viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.thic.marvelmovies.Model.models.CategoryModel;
import com.thic.marvelmovies.Model.models.Item;
import com.thic.marvelmovies.Model.Local.RoomModel;
import com.thic.marvelmovies.Model.Repository;

import java.util.List;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class ViewmodelData extends AndroidViewModel {

    public static MutableLiveData<List<CategoryModel>> categoryModelList = new MutableLiveData<>();
    public static MutableLiveData<Boolean> networkResult = new MutableLiveData<>();
    public static MutableLiveData<Item> clickListener = new MutableLiveData<>();
    private LiveData<List<RoomModel>> favoriteList;

    Repository repository;

    public ViewmodelData(@NonNull Application application) {
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

    //Get Lists
    public LiveData<List<RoomModel>> getFavoriteList(){ return favoriteList;}

    public MutableLiveData<List<CategoryModel>> getMovies (){
        categoryModelList.setValue(repository.getCategoryLists());
        return categoryModelList;
    }

}
