package com.thic.marvelmovies.Model.models;

import java.util.List;

public class CategoryModel {

    private int categoryID;
    private int IMDB;
    private String categoryTitle;
    private List<Item> movieList;


    public CategoryModel(int categoryID, int IMDB, String categoryTitle, List<Item> movieList) {
        this.categoryID = categoryID;
        this.IMDB = IMDB;
        this.categoryTitle = categoryTitle;
        this.movieList = movieList;
    }

    public CategoryModel() {
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

    public List<Item> getMovieList() {
        return movieList;
    }

    public void setMovieList(List<Item> movieList) {
        this.movieList = movieList;
    }

    public int getIMDB() {
        return IMDB;
    }

    public void setIMDB(int IMDB) {
        this.IMDB = IMDB;
    }


}
