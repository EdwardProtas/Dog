package com.example.cocktail.repo;

import com.example.cocktail.json.DrinkResponse;

import androidx.annotation.NonNull;
import io.reactivex.rxjava3.core.Observable;

public class CocktailRepoImpl implements CocktailsRepo {

    @NonNull
    @Override
    public Observable<DrinkResponse> getDring(String name) {
        return CocktailRemoteStorage.getInstance().getListDrink(name);
    }
}
