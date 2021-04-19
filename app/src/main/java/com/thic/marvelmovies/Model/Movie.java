
package com.thic.marvelmovies.Model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Movie {

    @SerializedName("results")
    @Expose
    private List<com.thic.marvelmovies.Result> results = null;

    public List<com.thic.marvelmovies.Result> getResults() {
        return results;
    }

    public void setResults(List<com.thic.marvelmovies.Result> results) {
        this.results = results;
    }

}
