package com.example.cocktail.ActivityListCoctail;

public interface ListCocktailPresenter {
    void setListCocktailView(ListCocktailView listCocktailView);
    void removeListCocktailView();
    void fetchCocktailList(String nameCocktail);
}
