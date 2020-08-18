package com.example.cocktail.ui.list_cocktail;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.cocktail.json.DrinkResponse;
import com.example.cocktail.ui.data_cocktail.DataListCocktail;
import com.example.cocktail.R;
import com.example.cocktail.repo.CocktailRepoImpl;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class ListCocktailActivity extends AppCompatActivity implements ListCocktailView {

    public static final String ACTIVITY_COCKTAIL = "ListCocktailActivity";

    private ListCocktailPresenter listCocktailPresenter;
    private ListCocktailAdapter activityListCocktailAdapter;
    private FrameLayout frameLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        frameLayout = findViewById(R.id.frameLayout);
        listCocktailPresenter = new ListCocktailPresenterImp(new CocktailRepoImpl());
        listCocktailPresenter.setListCocktailView(this);
        enterNameCocktail();
        initRecycler();

    }

    private void setItemAdapter() {
        activityListCocktailAdapter.setCocktailItemListener(nameBreeds -> {
            Intent intent = new Intent(ListCocktailActivity.this, DataListCocktail.class);
            intent.putExtra(ACTIVITY_COCKTAIL, nameBreeds);
            startActivity(intent);
        });
    }

    private void enterNameCocktail() {
        EditText etCocktailName = findViewById(R.id.etCocktailName);
        Button btnSearch = findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(view -> {
            String nameCocktail = etCocktailName.getText().toString();
            firtch(nameCocktail);
        });
    }

    private void firtch(String nameCocktail){
        if (!TextUtils.isEmpty(nameCocktail)) {
            listCocktailPresenter.fetchCocktailList(nameCocktail);
        } else
            Toast.makeText(getApplicationContext(), "Enter namecocktail", Toast.LENGTH_SHORT).show();
    }

    private void initRecycler() {
        RecyclerView rvCocktail = findViewById(R.id.rvCocktail);
        rvCocktail.setAdapter(new ListCocktailAdapter());
        rvCocktail.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false));
        activityListCocktailAdapter = (ListCocktailAdapter) rvCocktail.getAdapter();
        setItemAdapter();
    }

    @Override
    public void showListCocktail(DrinkResponse drink) {
        if(activityListCocktailAdapter != null) {
            activityListCocktailAdapter = null;
            initRecycler();
        }
        for (int i = 0; i < drink.getDrinks().size(); i++) {
            activityListCocktailAdapter.setApiCocktailRepositories(drink.getDrinks().get(i).getStrDrink());
            activityListCocktailAdapter.setApiCocktailURL(drink.getDrinks().get(i).getStrDrinkThumb());
        }
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
        listCocktailPresenter.removeListCocktailView();
    }
}
