package com.example.cocktail.repo;


import com.example.cocktail.json.DrinkResponse;

import androidx.annotation.NonNull;
import io.reactivex.rxjava3.core.Observable;

public interface CocktailsRepo {

    @NonNull
    Observable<DrinkResponse> getDring(String name);

}
