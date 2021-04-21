package com.thic.marvelmovies.Model.Network;


import com.thic.marvelmovies.Model.models.Movie;

import retrofit2.Call;
import retrofit2.http.GET;

public interface API {

    @GET("3/list/1?api_key=cddae2e178049deef0e0434c22f9ce53&language=en-US")
    Call<Movie>getData();
}
