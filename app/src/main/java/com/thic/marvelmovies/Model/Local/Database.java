package com.thic.marvelmovies.Model.Local;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@androidx.room.Database(entities = {RoomModel.class},version = 1)
public abstract class Database extends RoomDatabase {

    private static String database_name = "Favorites";
    private static Database instance;
    public abstract FavoriteDao favoriteDao();

    public static synchronized Database database(Context context){
        if (instance == null){
            instance = Room.databaseBuilder
                    (context.getApplicationContext(),Database.class,database_name)
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute(); }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void,Void,Void> {
        private FavoriteDao favoriteDao;
        private PopulateDbAsyncTask(Database database){
            favoriteDao = database.favoriteDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }



}
