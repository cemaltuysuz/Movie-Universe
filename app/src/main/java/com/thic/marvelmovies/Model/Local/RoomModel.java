package com.thic.marvelmovies.Model.Local;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
@Entity(tableName = "favorites")
public class RoomModel {

    @PrimaryKey
    @ColumnInfo(name = "movieID")
    private int movieID;

    @ColumnInfo(name = "movieTitle")
    private String movieTitle;

    @ColumnInfo(name = "movieDate")
    private String movieDate;

    @ColumnInfo(name = "movieLanguage")
    private String movieLanguage;

    @ColumnInfo(name = "movieIMDB")
    private String movieIMDb;

    @ColumnInfo(name = "movieOverview")
    private String overview;

    @ColumnInfo(name = "movieBackdrop")
    private String BackdropPath;

    public int getMovieID() {
        return movieID;
    }

    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getMovieDate() {
        return movieDate;
    }

    public void setMovieDate(String movieDate) {
        this.movieDate = movieDate;
    }

    public String getMovieLanguage() {
        return movieLanguage;
    }

    public void setMovieLanguage(String movieLanguage) {
        this.movieLanguage = movieLanguage;
    }

    public String getMovieIMDb() {
        return movieIMDb;
    }

    public void setMovieIMDb(String movieIMDb) {
        this.movieIMDb = movieIMDb;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getBackdropPath() {
        return BackdropPath;
    }

    public void setBackdropPath(String BackdropPath) {
        this.BackdropPath = BackdropPath;
    }

    public RoomModel(int movieID, String movieTitle, String movieDate, String movieLanguage, String movieIMDb, String overview, String BackdropPath) {
        this.movieID = movieID;
        this.movieTitle = movieTitle;
        this.movieDate = movieDate;
        this.movieLanguage = movieLanguage;
        this.movieIMDb = movieIMDb;
        this.overview = overview;
        this.BackdropPath = BackdropPath;
    }
}
