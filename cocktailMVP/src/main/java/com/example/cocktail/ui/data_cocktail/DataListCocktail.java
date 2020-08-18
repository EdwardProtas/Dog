package com.example.cocktail.ui.data_cocktail;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.cocktail.json.DrinkDetails;
import com.example.cocktail.ui.list_cocktail.ListCocktailActivity;
import com.example.cocktail.json.DrinkResponse;
import com.example.cocktail.R;
import com.example.cocktail.repo.CocktailRepoImpl;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class DataListCocktail extends AppCompatActivity implements DataListCocktailView {

    private DataListCocktailPresenter dataListCocktailPresenter;
    private String nameCoctail;
    private FrameLayout frameLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_image_cocktail);
        dataListCocktailPresenter = new DataListCocktailPresenterImp(new CocktailRepoImpl());
        dataListCocktailPresenter.setListCocktailView(this);
        frameLayout = findViewById(R.id.frameLayout);
        initIntent();
    }


    private void initIntent() {
        if (getIntent().hasExtra(ListCocktailActivity.ACTIVITY_COCKTAIL)) {
            nameCoctail = getIntent().getStringExtra(ListCocktailActivity.ACTIVITY_COCKTAIL);
            dataListCocktailPresenter.fetchCocktailList(nameCoctail);
        }
    }

    @Override
    public void showListCocktail(DrinkResponse drink) {
        ImageView imageCocktail = findViewById(R.id.imageCocktail);
        TextView cocktail = findViewById(R.id.cocktail);
        TextView txInstructions = findViewById(R.id.txInstructions);
        TextView txrIngredient = findViewById(R.id.txrIngredient);

        Glide.with(getApplicationContext()).load(drink.getDrinks().get(0).getStrDrinkThumb()).into(imageCocktail);

        DrinkDetails drinkDetails = drink.getDrinks().get(0);

        cocktail.setText(drinkDetails.getStrDrink());
        txInstructions.setMovementMethod(new ScrollingMovementMethod());
        txInstructions.setText(drinkDetails.getStrInstructions());
        txrIngredient.setText(String.format("%s, %s, %s", drinkDetails.getStrIngredient1(),
                drinkDetails.getStrIngredient2(), drinkDetails.getStrIngredient3()));
    }

    @Override
    public void showProgress() {
        frameLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        frameLayout.setVisibility(View.GONE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dataListCocktailPresenter.removeListCocktailView();
    }
}
