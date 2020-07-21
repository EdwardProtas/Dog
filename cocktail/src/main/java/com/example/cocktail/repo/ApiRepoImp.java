package com.example.cocktail.repo;

import com.example.cocktail.ConvertJson.Drink;

import androidx.annotation.NonNull;
import io.reactivex.rxjava3.core.Observable;

public class ApiRepoImp implements ApiRepo {

    @NonNull
    @Override
    public Observable<Drink> getDring(String name) {
        CocktailApi api = new CocktailApi();
        return api.getListDrink(name);
    }
}
