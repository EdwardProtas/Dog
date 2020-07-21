package com.example.cocktail.ActivityListCoctail;

import com.example.cocktail.ConvertJson.Drink;

public interface ListCocktailView {

    void showListCocktail(Drink drink);
    void showProgress();
    void hideProgress();

}
