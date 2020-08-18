package com.example.cocktailmvvm.ui.list_cocktail;

import com.example.cocktailmvvm.use_case.GetCocktailListUseCase;

import androidx.lifecycle.ViewModel;

public class ViewModelListCoctail extends ViewModel {

    private GetCocktailListUseCase getCocktailListUseCase;

    public void setGetCocktailListUseCase(String nameCocktail) {
        getCocktailListUseCase.listDrink(nameCocktail);
    }

    public GetCocktailListUseCase getGetCocktailListUseCase() {
        return getCocktailListUseCase;
    }
}
