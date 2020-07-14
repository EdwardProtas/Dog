package com.example.dog.repo;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DogsApi {

    @GET("/all")
    Call<Dogs> getDogs();

}
