package com.example.cocktail.ui.list_cocktail;

import com.example.cocktail.repo.CocktailsRepo;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;

public class ListCocktailPresenterImp implements ListCocktailPresenter {

    private ListCocktailView listCocktailView;
    private CocktailsRepo cocktailsRepo;
    private Disposable disposable;

    public ListCocktailPresenterImp(CocktailsRepo cocktailsRepo) {
        this.cocktailsRepo = cocktailsRepo;
    }

    @Override
    public void setListCocktailView(ListCocktailView listCocktailView) {
        this.listCocktailView = listCocktailView;
    }

    @Override
    public void removeListCocktailView() {
        listCocktailView = null;
        if(!disposable.isDisposed()){
            disposable.dispose();
        }
    }

    @Override
    public void fetchCocktailList(String nameCocktail) {
        listCocktailView.showProgress();
        disposable = cocktailsRepo.getDring(nameCocktail)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(drinkResponse -> {
                            listCocktailView.hideProgress();
                            listCocktailView.showListCocktail(drinkResponse);
                        },
                        throwable -> {
                            listCocktailView.hideProgress();
                        });
    }

}
