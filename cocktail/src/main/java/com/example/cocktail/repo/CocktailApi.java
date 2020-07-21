package com.example.cocktail.repo;

import com.example.cocktail.ConvertJson.CocktailGetCall;
import com.example.cocktail.ConvertJson.Drink;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CocktailApi {

    public static final String URL_COCKTAIL_ALL = "https://www.thecocktaildb.com";

    public  @NonNull Observable<Drink> getListDrink(String nameCocktail) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL_COCKTAIL_ALL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return Observable.create((ObservableOnSubscribe<Response>) emitter ->
                emitter.onNext(retrofit.create(CocktailGetCall.class).getDrink(nameCocktail).execute()))
                .subscribeOn(Schedulers.io())
                .map(response -> (Drink) response.body());
    }
}
