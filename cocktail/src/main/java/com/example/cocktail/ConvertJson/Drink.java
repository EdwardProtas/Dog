package com.example.cocktail.ConvertJson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Drink {

    @SerializedName("drinks")
    private List<DataDrink> drinks;

    public List<DataDrink> getDrinks() {
        return drinks;
    }
}
