package com.example.dog.repo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Dogs {

    @SerializedName("message")
    private List<String> message;

    public List<String> getMessage() {
        return message;
    }
}
