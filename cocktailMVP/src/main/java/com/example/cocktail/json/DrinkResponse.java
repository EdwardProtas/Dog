package com.example.cocktail.json;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DrinkResponse {

    @SerializedName("drinks")
    private List<DrinkDetails> drinks;

    public List<DrinkDetails> getDrinks() {
        return drinks;
    }
}
