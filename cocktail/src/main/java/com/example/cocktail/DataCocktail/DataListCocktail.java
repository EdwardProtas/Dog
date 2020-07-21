package com.example.cocktail.DataCocktail;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.cocktail.ActivityListCoctail.ListCocktailActivity;
import com.example.cocktail.ConvertJson.Drink;
import com.example.cocktail.R;
import com.example.cocktail.repo.ApiRepoImp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class DataListCocktail extends AppCompatActivity implements DataListCocktailView {

    private DataListCocktailPresenter dataListCocktailPresenter;
    private String nameCoctail;
    private ProgressBar progress;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_image_cocktail);
        dataListCocktailPresenter = new DataListCocktailPresenterImp(new ApiRepoImp());
        dataListCocktailPresenter.setListCocktailView(this);
        progress = findViewById(R.id.progress);
        initIntent();
    }


    private void initIntent() {
        if (getIntent().hasExtra(ListCocktailActivity.ActivityListCocktail)) {
            nameCoctail = getIntent().getStringExtra(ListCocktailActivity.ActivityListCocktail);
            dataListCocktailPresenter.fetchCocktailList(nameCoctail);
        }
    }

    @Override
    public void showListCocktail(Drink drink) {
        ImageView imageCocktail = findViewById(R.id.imageCocktail);
        TextView cocktail = findViewById(R.id.cocktail);
        TextView strInstructions = findViewById(R.id.strInstructions);
        TextView strIngredient = findViewById(R.id.strIngredient);
        Glide.with(getApplicationContext()).load(drink.getDrinks().get(0).getStrDrinkThumb()).into(imageCocktail);
        cocktail.setText(drink.getDrinks().get(0).getStrDrink());
        strInstructions.setText(drink.getDrinks().get(0).getStrInstructions());
        strIngredient.setText(drink.getDrinks().get(0).getStrIngredient1() + ", " +
                drink.getDrinks().get(0).getStrIngredient2() + ", " +
                drink.getDrinks().get(0).getStrIngredient3());
    }

    @Override
    public void showProgress() {
        progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progress.setVisibility(View.GONE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dataListCocktailPresenter.removeListCocktailView();
    }
}
