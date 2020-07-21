package com.example.cocktail.repo;


import com.example.cocktail.ConvertJson.Drink;

import androidx.annotation.NonNull;
import io.reactivex.rxjava3.core.Observable;

public interface ApiRepo {

    @NonNull
    Observable<Drink> getDring(String name);

}
