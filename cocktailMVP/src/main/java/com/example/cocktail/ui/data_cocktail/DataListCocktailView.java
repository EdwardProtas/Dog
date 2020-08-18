package com.example.cocktail.ui.data_cocktail;

import com.example.cocktail.json.DrinkResponse;

public interface DataListCocktailView {

    void showListCocktail(DrinkResponse drinkResponse);
    void showProgress();
    void hideProgress();

}
