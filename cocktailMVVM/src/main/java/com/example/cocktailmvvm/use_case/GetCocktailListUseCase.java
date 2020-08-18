package com.example.cocktailmvvm.use_case;

import android.util.Log;

import com.example.cocktailmvvm.model_json.DrinkResponse;
import com.example.cocktailmvvm.retrofit.CocktailRemoteStorage;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetCocktailListUseCase {

    public LiveData<DrinkResponse> listDrink(String nameCocktail) {
        MutableLiveData<DrinkResponse> drinkResponseLiveData = new MutableLiveData<>();
        CocktailRemoteStorage.getInstance().invoke()
                .getDrink(nameCocktail).enqueue(new Callback<DrinkResponse>() {
            @Override
            public void onResponse(@NotNull Call<DrinkResponse> call, @NotNull Response<DrinkResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    drinkResponseLiveData.setValue(response.body());
                }
            }
            @Override
            public void onFailure(@NotNull Call<DrinkResponse> call, @NotNull Throwable t) {
                Log.d("Tag", Objects.requireNonNull(t.getMessage()));
            }
        });
        return drinkResponseLiveData;
    }
}
