package com.example.dog.repo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Dog {

    @SerializedName("message")
    private Breeds message;

    public Breeds getMessage() {
        return message;
    }

}
