package com.example.cocktail.ActivityListCoctail;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.cocktail.ConvertJson.Drink;
import com.example.cocktail.DataCocktail.DataListCocktail;
import com.example.cocktail.R;
import com.example.cocktail.repo.ApiRepoImp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class ListCocktailActivity extends AppCompatActivity implements ListCocktailView {

    private ListCocktailPresenter listCocktailPresenter;
    public static final String ActivityListCocktail = "ListCocktailActivity";
    private ListCocktailAdapter activityListCocktailAdapter;
    private ProgressBar progress;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progress = findViewById(R.id.progress);
        listCocktailPresenter = new ListCocktailPresenterImp(new ApiRepoImp());
        listCocktailPresenter.setListCocktailView(this);
        initRecycler();
        enterNameCocktail();
        presItemAdapter();
    }

    private void presItemAdapter() {
        activityListCocktailAdapter.setCocktailItemListener(nameBreeds -> {
            Intent intent = new Intent(ListCocktailActivity.this, DataListCocktail.class);
            intent.putExtra(ActivityListCocktail, nameBreeds);
            startActivity(intent);
        });

    }

    private void enterNameCocktail() {
        EditText enterCocktail;
        Button search;
        enterCocktail = findViewById(R.id.enterCocktail);
        search = findViewById(R.id.search);
        search.setOnClickListener(view -> {
            if (!enterCocktail.getText().toString().equals("")) {
                listCocktailPresenter.fetchCocktailList(enterCocktail.getText().toString());
            } else
                Toast.makeText(getApplicationContext(), "Enter namecocktail", Toast.LENGTH_SHORT).show();
        });
    }

    private void initRecycler() {
        RecyclerView recyclerView;
        recyclerView = findViewById(R.id.recyclerView_breeds);
        recyclerView.setAdapter(new ListCocktailAdapter());
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false));
        activityListCocktailAdapter = (ListCocktailAdapter) recyclerView.getAdapter();
    }

    @Override
    public void showListCocktail(Drink drink) {
        for (int i = 0; i < drink.getDrinks().size(); i++) {
            activityListCocktailAdapter.setApiCocktailRepositories(drink.getDrinks().get(i).getStrDrink());
            activityListCocktailAdapter.setApiCocktailURL(drink.getDrinks().get(i).getStrDrinkThumb());
        }
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
        listCocktailPresenter.removeListCocktailView();
    }
}
