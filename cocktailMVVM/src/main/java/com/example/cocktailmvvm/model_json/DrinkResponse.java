package com.example.cocktailmvvm.model_json;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DrinkResponse {

    @SerializedName("drinks")
    private List<DrinkResponseDetails> drinks;

    public List<DrinkResponseDetails> getDrinks() {
        return drinks;
    }
}
