package com.example.cocktail.ActivityListCoctail;

import com.example.cocktail.repo.ApiRepo;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;

public class ListCocktailPresenterImp implements ListCocktailPresenter {

    private ListCocktailView listCocktailView;
    private ApiRepo apiRepo;
    private Disposable disposable;

    public ListCocktailPresenterImp(ApiRepo apiRepo) {
        this.apiRepo = apiRepo;
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
        disposable = apiRepo.getDring(nameCocktail)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(drink -> {
                            listCocktailView.hideProgress();
                            listCocktailView.showListCocktail(drink);
                        },
                        throwable -> {
                            listCocktailView.hideProgress();
                        });
    }

}
