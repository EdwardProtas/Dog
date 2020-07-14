package com.example.dog.repo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

class Breeds {
    

    @SerializedName("affenpinscher")
    private String affenpinscher;
    @SerializedName("african")
    private String african;

    public String getAffenpinscher() {
        return affenpinscher;
    }

    public String getBasenji() {
        return african;
    }


}
