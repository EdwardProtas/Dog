package com.example.cocktail.ConvertJson;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CocktailGetCall {

@GET("/api/json/v1/1/search.php")
    Call<Drink> getDrink(@Query("s") String nameCocktail);

}
