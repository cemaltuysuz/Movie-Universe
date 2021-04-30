package com.thic.marvelmovies.Model.Local;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
@Entity(tableName = "favorites")
public class RoomModel {

    @PrimaryKey(autoGenerate = true)
    private int favoriteID;

    private int movieID;

    @Ignore
    public RoomModel(int favoriteID, int movieID) {
        this.favoriteID = favoriteID;
        this.movieID = movieID;
    }

    public RoomModel(int movieID) {
        this.movieID = movieID;
    }

    public int getFavoriteID() {
        return favoriteID;
    }

    public void setFavoriteID(int favoriteID) {
        this.favoriteID = favoriteID;
    }

    public int getMovieID() {
        return movieID;
    }

    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }
}
