package com.example.cocktailmvvm.ui.list_cocktail;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.example.cocktailmvvm.R;
import com.example.cocktailmvvm.ui.data_cocktail.DataListCocktail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ActivityListCocktail extends AppCompatActivity{

    public static final String ACTIVITY_LIST_COCKTAIL = "ACTIVITY_LIST_COCKTAIL";

    private ViewModelListCoctail viewModelListCoctail;
    private FrameLayout frameLayout;
    private AdapterListCocktail adapterListCocktail;
    private Application mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        frameLayout = findViewById(R.id.frameLayout);
        initViewModel();
        searchCocktail();
        initRecyclerView();
    }

    private void searchCocktail() {
        Button btnSearch = findViewById(R.id.btnSearch);
        EditText etCocktailName = findViewById(R.id.etCocktailName);
        if (!TextUtils.isEmpty(etCocktailName.getText().toString())) {
            btnSearch.setOnClickListener(view -> {
                String nameCocktailSearch = etCocktailName.getText().toString();
                firtch(nameCocktailSearch);
            });
        }
    }

    private void firtch(String nameCocktailSearch) {
    }


    private void initRecyclerView() {
        RecyclerView rvCocktail = findViewById(R.id.rvCocktail);
        rvCocktail.setAdapter(new AdapterListCocktail());
        rvCocktail.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false));
        adapterListCocktail = (AdapterListCocktail) rvCocktail.getAdapter();
        setItemAdapter();
    }

    private void setItemAdapter() {
        adapterListCocktail.setCocktailItemListener(nameBreeds -> {
            Intent intent = new Intent(ActivityListCocktail.this, DataListCocktail.class);
            intent.putExtra(ACTIVITY_LIST_COCKTAIL, nameBreeds);
            startActivity(intent);
        });
    }

    private void initViewModel() {
        viewModelListCoctail = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(mContext))
                .get(ViewModelListCoctail.class);
    }
}
