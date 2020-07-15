package com.example.dog.repo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Breeds {

    @SerializedName("affenpinscher")
    private List<String> affenpinscher;
    @SerializedName("african")
    private List<String> african;
    @SerializedName("bullterrier")
    private List<String> bullterrier;
    @SerializedName("terrier")
    private List<String> terrier;

    public List<String> getAffenpinscher() {
        return affenpinscher;
    }

    public List<String> getAfrican() {
        return african;
    }

    public List<String> getBullterrier() {
        return bullterrier;
    }

    public List<String> getTerrier() {
        return terrier;
    }
}
