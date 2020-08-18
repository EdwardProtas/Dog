package com.example.cocktailmvvm.retrofit;


import com.example.cocktailmvvm.model_json.DrinkResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CocktailApi {

@GET("/api/json/v1/1/search.php")
    Call<DrinkResponse> getDrink(@Query("s") String nameCocktail);

}
