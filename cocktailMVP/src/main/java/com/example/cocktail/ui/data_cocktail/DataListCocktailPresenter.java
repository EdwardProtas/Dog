package com.example.cocktail.ui.data_cocktail;

public interface DataListCocktailPresenter {

    void setListCocktailView(DataListCocktailView dataListCocktailView);
    void removeListCocktailView();
    void fetchCocktailList(String nameCocktail);

}
