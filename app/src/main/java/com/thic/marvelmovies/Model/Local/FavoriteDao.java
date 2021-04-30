package com.thic.marvelmovies.Model.Local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Room;

import java.util.List;

@Dao
public interface FavoriteDao {

    @Insert
    void favoriteInsert(RoomModel model);

    @Delete
    void favoriteDel(RoomModel model);

    @Query("Select * from favorites")
    LiveData<List<RoomModel>> getFavorites();

}
