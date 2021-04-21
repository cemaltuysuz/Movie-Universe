package com.thic.marvelmovies.Model;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.thic.marvelmovies.Model.Local.Database;
import com.thic.marvelmovies.Model.Local.FavoriteDao;
import com.thic.marvelmovies.Model.Local.RoomModel;
import com.thic.marvelmovies.Model.Network.API;
import com.thic.marvelmovies.Model.Network.ApiUtils;
import com.thic.marvelmovies.Model.models.Item;
import com.thic.marvelmovies.Model.models.Movie;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {

    public static API dataApi = ApiUtils.api();
    FavoriteDao dao;

    public static LiveData<List<RoomModel>> AllData;
    public static List<Item> AllMovies;

    public Repository(Application application) {
        Database database = Database.database(application);
        dao = database.favoriteDao();
        AllData = dao.getFavorites();
        getMovies();
    }

    //Actions
    public void insert(RoomModel insertModel) {
        new InsertAsyncTask(dao).execute(insertModel);
    }

    public void delete(RoomModel delModel) {
        new DeleteAsyncTask(dao).execute(delModel);
    }

    //  <------  Get Data List  -------->

    // Local Lists
    public LiveData<List<RoomModel>> getAllData() {
        return AllData;
    }

    // Network List
    public List<Item> getAllMovies() {
        return AllMovies;
    }


    //  <------  Asynctask  -------->

    private static class InsertAsyncTask extends AsyncTask<RoomModel, Void, Void> {
        private FavoriteDao dao;

        private InsertAsyncTask(FavoriteDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(RoomModel... Models) {
            dao.favoriteInsert(Models[0]);
            return null;
        }
    }

    private static class DeleteAsyncTask extends AsyncTask<RoomModel, Void, Void> {
        private FavoriteDao dao;

        private DeleteAsyncTask(FavoriteDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(RoomModel... models) {
            dao.favoriteDel(models[0]);
            return null;
        }
    }

    //  <------  Get movies with retrofit  -------->

    public void getMovies() {
        dataApi.getData().enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                List<Item>itemList = response.body().getItems();
                for (Item i:itemList){
                    Log.d("MovieID",i.getId().toString());
                    Log.d("MovieID",i.getTitle());
                    Log.d("MovieID",i.getVoteAverage().toString());
                    Log.d("*******","*******");
                }
                AllMovies = itemList;
                Log.d("Yonerge:","Succes 2");
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                Log.d("Yonerge:",t.getMessage());
            }
        });
    }
}
