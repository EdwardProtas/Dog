package com.example.cocktail.repo;

import com.example.cocktail.json.CocktailApi;
import com.example.cocktail.json.DrinkResponse;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CocktailRemoteStorage {

    public static CocktailRemoteStorage INSTANCE;
    public static final String URL_COCKTAIL_ALL = "https://www.thecocktaildb.com";
    private Retrofit retrofit;

    public CocktailRemoteStorage() {
        retrofit = new Retrofit.Builder()
                .baseUrl(URL_COCKTAIL_ALL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


    public static CocktailRemoteStorage getInstance(){
        if(INSTANCE == null){
            INSTANCE = new CocktailRemoteStorage();
        }
        return INSTANCE;
    }

    public  @NonNull Observable<DrinkResponse> getListDrink(String nameCocktail) {
        return Observable.create((ObservableOnSubscribe<Response>) emitter ->
                emitter.onNext(retrofit.create(CocktailApi.class).getDrink(nameCocktail).execute()))
                .subscribeOn(Schedulers.io())
                .map(response -> (DrinkResponse) response.body());
    }
}
