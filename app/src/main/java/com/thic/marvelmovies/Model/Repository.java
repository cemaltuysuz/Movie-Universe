package com.thic.marvelmovies.Model;

import android.app.Application;
import android.database.sqlite.SQLiteMisuseException;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.thic.marvelmovies.Model.Local.Database;
import com.thic.marvelmovies.Model.Local.FavoriteDao;
import com.thic.marvelmovies.Model.Local.RoomModel;
import com.thic.marvelmovies.Model.Network.API;
import com.thic.marvelmovies.Model.Network.ApiUtils;
import com.thic.marvelmovies.Model.models.CategoryModel;
import com.thic.marvelmovies.Model.models.Item;
import com.thic.marvelmovies.Model.models.Movie;
import com.thic.marvelmovies.Viewmodel.ViewmodelData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {

    public static API dataApi = ApiUtils.api();
    FavoriteDao dao;

    public static LiveData<List<RoomModel>> AllData;

    //Actions
    public List<Item> AllMovies = new ArrayList<>();
    public List<CategoryModel>categoryLists = new ArrayList<>();
    public boolean dataCheck = false;

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
    public List<CategoryModel> getCategoryLists() {
        return categoryLists;
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

                AllMovies.addAll(itemList);
                if (AllMovies.size()>0){
                    dataCheck=true;
                    run();
                    ViewmodelData.networkResult.setValue(true);
                }
            }
            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                ViewmodelData.networkResult.setValue(false);
            }
        });
    }
        public void run(){
           for (int i = 0; i <AllMovies.size(); i++){
               List<Item> temporaryList = new ArrayList<>();
               Item item = AllMovies.get(i);
               if (!categoryLists.isEmpty()){
                   boolean check = false;

                   for (int j = 0; j<categoryLists.size();j++){
                       if (categoryLists.get(j).getIMDB() == item.getVoteAverage().intValue()){
                           Log.d("EKLEME VAR ! =",categoryLists.get(j).getCategoryTitle()+" listesine "+
                                   item.getVoteAverage().intValue() + " IMDB Lİ " + item.getTitle() + " film eklendi.");
                           categoryLists.get(j).getMovieList().add(item);
                           check = true;
                           break;
                       }
                   }
                   if (check == false){

                       Log.d("YENİ LİSTE VAR ! =", item.getVoteAverage().intValue() +" IMDBLİ film listesi "+
                               item.getVoteAverage().intValue() + " IMDB Lİ " + item.getTitle() + " film ile oluşturuldu.");
                       temporaryList.clear();
                       temporaryList.add(item);
                       categoryLists.add(new CategoryModel(AllMovies.get(i).getId()
                               ,AllMovies.get(i).getVoteAverage().intValue()
                               ,"IMDb " + item.getVoteAverage().intValue(),temporaryList));
                       temporaryList.clear();
                   }
               }else {
                   temporaryList.clear();
                   temporaryList.add(item);
                   categoryLists.add(new CategoryModel(item.getId()
                           ,item.getVoteAverage().intValue()
                           ,"IMDb " + item.getVoteAverage().intValue(),temporaryList));
                   temporaryList.clear();
               }
            }
           sortList(categoryLists);
           for (int i = 0; i<categoryLists.size();i++){
                 Log.d("Category Title :",categoryLists.get(i).getCategoryTitle());
                 Log.d("********","*********");

                  for (int j = 0;j<categoryLists.get(i).getMovieList().size();j++){
                   Log.d("Movie Title :",categoryLists.get(i).getMovieList().get(j).getTitle());
                 Log.d("Movie IBDM :",String.valueOf(categoryLists.get(i).getMovieList().get(j).getVoteAverage().intValue()));
             }
           }
        }


    private void sortList(List<CategoryModel> categoryLists) {
        Collections.sort(categoryLists, new Comparator<CategoryModel>() {
            @Override
            public int compare(CategoryModel o1, CategoryModel o2) {
                Integer num1 = o1.getIMDB();
                Integer num2 = o2.getIMDB();
                return num1.compareTo(num2);
            }
        });
    }



}
