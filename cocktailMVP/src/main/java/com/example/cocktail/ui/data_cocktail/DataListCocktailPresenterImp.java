package com.example.cocktail.ui.data_cocktail;

import com.example.cocktail.repo.CocktailsRepo;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;

public class DataListCocktailPresenterImp implements DataListCocktailPresenter {

    private CocktailsRepo cocktailsRepo;
    private Disposable disposable;
    private DataListCocktailView dataListCocktailView;

    public DataListCocktailPresenterImp(CocktailsRepo cocktailsRepo) {
        this.cocktailsRepo = cocktailsRepo;
    }

    @Override
    public void setListCocktailView(DataListCocktailView dataListCocktailView) {
        this.dataListCocktailView = dataListCocktailView;
    }

    @Override
    public void removeListCocktailView() {
        dataListCocktailView = null;
        if(!disposable.isDisposed()){
            disposable.dispose();
        }
    }

    @Override
    public void fetchCocktailList(String nameCocktail) {
        dataListCocktailView.showProgress();
        disposable = cocktailsRepo.getDring(nameCocktail)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(drinkResponse -> {
                            dataListCocktailView.hideProgress();
                            dataListCocktailView.showListCocktail(drinkResponse);

                        },
                        throwable -> dataListCocktailView.hideProgress());
    }
}
