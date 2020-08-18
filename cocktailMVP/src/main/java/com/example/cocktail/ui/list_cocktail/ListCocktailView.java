package com.example.cocktail.ui.list_cocktail;

import com.example.cocktail.json.DrinkResponse;

public interface ListCocktailView {

    void showListCocktail(DrinkResponse drinkResponse);
    void showProgress();
    void hideProgress();

}
