package com.thic.marvelmovies.Model.Network;

public class ApiUtils {

    public static final String baseUrl = "https://api.themoviedb.org/";

    public static API api(){

        return RetrofitClient.getClient(baseUrl).create( API.class);
    }
}
