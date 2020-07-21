package com.example.cocktail.DataCocktail;

public interface DataListCocktailPresenter {

    void setListCocktailView(DataListCocktailView dataListCocktailView);
    void removeListCocktailView();
    void fetchCocktailList(String nameCocktail);

}
