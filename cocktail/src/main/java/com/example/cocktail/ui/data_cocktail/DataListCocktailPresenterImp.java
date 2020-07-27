package com.example.cocktail.ui.data_cocktail;

import com.example.cocktail.repo.CocktailsRepo;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;

public class DataListCocktailPresenterImp implements DataListCocktailPresenter {

    private CocktailsRepo apiRepo;
    private Disposable disposable;
    private DataListCocktailView dataListCocktailView;

    public DataListCocktailPresenterImp(CocktailsRepo apiRepo) {
        this.apiRepo = apiRepo;
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
        disposable = apiRepo.getDring(nameCocktail)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(drink -> {
                            dataListCocktailView.hideProgress();
                            dataListCocktailView.showListCocktail(drink);

                        },
                        throwable -> dataListCocktailView.hideProgress());
    }
}
