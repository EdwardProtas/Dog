package com.example.cocktail.ui.list_cocktail;

public interface ListCocktailPresenter {
    void setListCocktailView(ListCocktailView listCocktailView);
    void removeListCocktailView();
    void fetchCocktailList(String nameCocktail);
}
