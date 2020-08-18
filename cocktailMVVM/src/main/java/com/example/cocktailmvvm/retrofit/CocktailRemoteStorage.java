package com.example.cocktailmvvm.retrofit;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CocktailRemoteStorage {

    private static final String URL_COCKTAIL_ALL = "https://www.thecocktaildb.com";
    private static CocktailRemoteStorage INSTANCE;

    public static CocktailRemoteStorage getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CocktailRemoteStorage();
        }
        return INSTANCE;
    }

    public CocktailApi invoke(){
        return new Retrofit.Builder().baseUrl(URL_COCKTAIL_ALL)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(CocktailApi.class);
    }
}